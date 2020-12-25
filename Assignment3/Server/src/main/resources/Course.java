import java.util.*;
public class Course {

	private final int courseNum;
	private final String courseName;
	private final Vector<Integer> kdamCoursesList;
	private final int numOfMaxStudents;
	private int numOfSeatsAvailable;
	private AVLTree listOfStudents;
	
	public Course(int courseNum,String courseName,Vector<Integer> kdamCoursesList,int numOfMaxStudnets) {
		this.courseNum=courseNum;
		this.courseName=courseName;
		this.kdamCoursesList=kdamCoursesList;
		this.numOfMaxStudents=numOfMaxStudents;
		this.numOfSeatsAvailable=numOfMaxStudents;
	}
	public int getCourseNum() { return courseNum;}
	public String courseName() {return courseName;}
	public Vector<Integer> getKdamCoursesList() {return kdamCoursesList;}
	public int getNumOfMaxStudenst() {return numOfMaxStudents;}
	public int getNumOfSeatsAvailable() {return numOfSeatsAvailable;}
	public AVLTree getListOfStudents() {return listOfStudents;}
	
	public boolean takeSeat() {
		if(numOfSeatsAvailable>0) {
			numOfSeatsAvailable--;
			return true;
		}
		return false;
	}
	
	
}
