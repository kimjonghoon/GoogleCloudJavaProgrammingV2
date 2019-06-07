package net.java_school.user;

public interface UserService {
	
	public void addUser(GaeUser gaeUser);
	
	public GaeUser findUser(String email);

}
