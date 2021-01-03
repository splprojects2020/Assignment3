package bgu.spl.net.resources;
import java.util.Vector;

public class Student extends User{
	
	private Vector<Integer> registerdCourses;
	
	public Student(String userName,String password) {
		super(userName,password);
		registerdCourses=new Vector<Integer>();
	}
	public Vector<Integer> getRegisterdCourses(){
		return registerdCourses;
	}
	public boolean registerCourse(int courseNum) {
		if(database.registerCourse(courseNum,this)) {
			registerdCourses.add(courseNum);
			registerdCourses.sort((a,b)->{return database.getCoursePosition(a)-database.getCoursePosition(b);});
			return true;
		}
		return false;
	}
	public String studentStatus() {
		return "Student: "+userName+ " \n"+
				"Courses: "+registerdCourses.toString();
	}
	public String isRegisterdToCourse(int courseNum) {
		if(registerdCourses.contains(courseNum))
			return "REGISTERD";
		return "NOT REGISTERD";
	}
	public boolean unregister(int courseNum) {
		if(!registerdCourses.contains(courseNum)) 
			return false;
		database.unregisterStudentFromCourse(userName,courseNum);
		registerdCourses.remove((Integer)courseNum);
		return true;
	}
	public String getMyCourses() {
		return registerdCourses.toString();
	}

}
