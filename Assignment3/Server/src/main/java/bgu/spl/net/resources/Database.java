package bgu.spl.net.resources;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

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
		coursesList = new HashMap<Integer,Course>();
		usersList = new HashMap<String,User>();
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
		try {
			Scanner scanner = new Scanner(new File(coursesFilePath));
			while (scanner.hasNextLine()) {
				
				String line=(scanner.nextLine());
				int courseNumber=Integer.parseInt(line.substring(0, line.indexOf('|')));
				line=line.substring(line.indexOf('|')+1,line.length());;
				String courseName=line.substring(0, line.indexOf('|'));
				line=line.substring(line.indexOf('|')+1,line.length());
				String kdamCourseString=line.substring(0, line.indexOf('|'));
				String temp = "";
				Vector<Integer> kdamCourse=new Vector<Integer>();
				for (int i = 0; i < kdamCourseString.length(); i++) { 
					if(kdamCourseString.charAt(i)>='0' && kdamCourseString.charAt(i)<='9')
						temp =temp+ kdamCourseString.charAt(i); 
					else if(kdamCourseString.charAt(i)==',') {
						kdamCourse.add((Integer.parseInt(temp)));
						temp="";
	        	}  		
	        } 
	       
			int maxPlace=Integer.parseInt(line.substring(line.indexOf('|')+1,line.length()));	
			Course newCourse = new Course(courseNumber,courseName,kdamCourse,maxPlace);
			coursesList.putIfAbsent(courseNumber, newCourse);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			return false;
		}	
		return true;
	}
	
	public User getUser(String userName) {
		return usersList.get(userName);		
	}
	
	public boolean register(String userName,String password,boolean admin) {
		if(usersList.containsKey(userName))
			return false;
		if(admin) {
			usersList.put(userName, new Admin(userName,password));
		}
		else {
			usersList.put(userName, new Student(userName,password));
		}
		return true;
	}
	public boolean login(String userName,String password) {
		User currentUser = usersList.get(userName);
		if(currentUser==null || currentUser.isLogged()) //check if the user is not registered or already logged in
			return false;
		if(!currentUser.getPassword().equals(password))
			return false;
		currentUser.login();
		return true;
	}
	public boolean logout(User currentUser) {//TODO
		if(currentUser==null)
			return false;
		currentUser.logout();
		return true;
	}
	
	public boolean registerCourse(int courseName,Student currentUser) {

		Course desiredCourse = coursesList.get(courseName);
		if(desiredCourse==null || desiredCourse.getNumOfSeatsAvailable()<=0 |
				!currentUser.getRegisterdCourses().containsAll(desiredCourse.getKdamCoursesList()) |
				currentUser.getRegisterdCourses().contains(desiredCourse.getCourseNum())) 
			return false;
		desiredCourse.takeSeat();
		desiredCourse.setRoot(desiredCourse.getListOfStudents().insert(desiredCourse.getRoot(), currentUser.getUsername()));	
		return true;
	}
	public String courseStatus(int courseNum) {	
		Course desiredCourse = coursesList.get(courseNum);
		if(desiredCourse==null) return "not-valid";
		return desiredCourse.getCourseStatus();
	}
	public String studentStatus(String studentName) {
		User desiredStudent = usersList.get(studentName);
		if(desiredStudent instanceof Admin | desiredStudent==null) 
			return "not-valid";
		return ((Student)desiredStudent).studentStatus();
	}
	public void unregisterStudentFromCourse(String studentName,int courseNum) {
		Course desiredCourse = coursesList.get(courseNum);
		desiredCourse.setRoot(desiredCourse.getListOfStudents().deleteNode(desiredCourse.getRoot(), studentName));
		desiredCourse.releaseSeat();
	}
	public String kdamCheck(int courseNumber) {
		Course desiredCourse=coursesList.get(courseNumber);
		return desiredCourse.kdamCheck();
	}
	
}
