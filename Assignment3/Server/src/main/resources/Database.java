import java.util.HashMap;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

	private HashMap<Integer,Course> coursesList;
	private HashMap<String,User> usersList;
	//to prevent user from creating new Database
	private Database() {
		// TODO: implement
	}

	/**
	 * Thread-safe singleton
	 */
	private static class DatabaseHolder{
		private static Database instance = new Database();
	}

	public static Database getInstance() {
		return DatabaseHolder.instance;
	}
	
	/**
	 * loades the courses from the file path specified 
	 * into the Database, returns true if successful.
	 */
	boolean initialize(String coursesFilePath) {
		// TODO: implement
		return false;
	}
	
	public boolean register(String userName,String password) {
		if(usersList.containsKey(userName))
			return false;
		usersList.put(userName, new User(userName,password));
		return true;
	}
	public boolean login(String userName,String password) {
		User currentUser = usersList.get(userName);
		if(currentUser==null) return false;
		if(!currentUser.getPassword().equals(password)) return false;
		currentUser.login();
		
		
	}

}
