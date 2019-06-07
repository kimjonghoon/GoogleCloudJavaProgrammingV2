package net.java_school.user;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import net.java_school.spring.security.AppRole;

@SuppressWarnings("serial")
@Entity
public class GaeUser implements Serializable {
	public String userId;
	@Id public String email;
	public String nickname;
	public Set<AppRole> authorities;
	public boolean enabled;
	
	public GaeUser() {}
	
	public GaeUser(String userId, String email, String nickname) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.authorities = EnumSet.of(AppRole.ROLE_GOOGLE);
	}
	
	public GaeUser(String userId, String email, String nickname, Set<AppRole> authorities, boolean enabled) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<AppRole> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<AppRole> authorities) {
		this.authorities = authorities;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}