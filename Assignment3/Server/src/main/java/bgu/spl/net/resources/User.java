import java.util.*;

public class User {
	
	protected final String userName;
	protected final String password;
	protected boolean isLogged;
	protected Database database;
	
	public User(String userName,String password) {
		this.userName=userName;
		this.password=password;
		this.database=Database.getInstance();
		isLogged=false;
	}
	public String getUsername() {return userName;}
	public String getPassword() {return password;}
	
	public boolean isLogged() {
		return isLogged;
	}
	public void login() {
		isLogged=true;
	}
	public void logout() {
		isLogged=false;
	}
}
