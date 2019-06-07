package net.java_school.user;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

import net.java_school.spring.security.AppRole;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser(GaeUser gaeUser) {
		GaeUser user = this.findUser(gaeUser.getEmail());
		if (user == null) {
			gaeUser.getAuthorities().add(AppRole.ROLE_USER);
			gaeUser.setEnabled(true);
			ofy().save().entity(gaeUser).now();
		} else {
			if(gaeUser.isEnabled()) {
				Key<GaeUser> key = Key.create(GaeUser.class, gaeUser.getEmail());
				GaeUser storedGaeUser = ofy().load().key(key).now();
				storedGaeUser.setNickname(gaeUser.getNickname());
				ofy().save().entity(storedGaeUser).now();
			}
		}
	}

	/* Why was the ObjectifyService.run() method used?
	 * https://groups.google.com/forum/#!topic/objectify-appengine/ixhH1eQaycY
	 * https://github.com/objectify/objectify/wiki/Utilising-Objectify-Outside-of-Context
	 */
	@Override
	public GaeUser findUser(String email) {
		Key<GaeUser> gaeUserKey = Key.create(GaeUser.class, email);
		GaeUser gaeUser = ObjectifyService.run(new Work<GaeUser>() {
            @Override
            public GaeUser run() {
                GaeUser gaeUser = ofy().load().key(gaeUserKey).now();
                return gaeUser;
            }
        });
		return gaeUser;
	}

}