package bgu.spl.net.resources;
import java.util.*;

import bgu.spl.net.resources.AVLTree.Node;
public class Course {

	private final int courseNum;
	private final String courseName;
	private final Vector<Integer> kdamCoursesList;
	private final int numOfMaxStudents;
	private int numOfSeatsAvailable;
	private AVLTree listOfStudents;
	private Node root;

	public Course(int courseNum,String courseName,Vector<Integer> kdamCoursesList,int numOfMaxStudents) {
		this.courseNum=courseNum;
		this.courseName=courseName;
		this.kdamCoursesList=kdamCoursesList;
		this.numOfMaxStudents=numOfMaxStudents;
		this.numOfSeatsAvailable=numOfMaxStudents;
		this.root=null;
	}
	public int getCourseNum() { return courseNum;}
	public String courseName() {return courseName;}
	public Vector<Integer> getKdamCoursesList() {return kdamCoursesList;}
	public String kdamCheck() {return kdamCoursesList.toString();}
	public int getNumOfMaxStudenst() {return numOfMaxStudents;}
	public int getNumOfSeatsAvailable() {return numOfSeatsAvailable;}
	public AVLTree getListOfStudents() {return listOfStudents;}
	public Node getRoot() {return root;}
	public String getCourseStatus() {
		String toString = 
				  "Course: ("+courseNum+") " +courseName+  "\n"
				+ "Seats Available: " +numOfSeatsAvailable+"/"+numOfMaxStudents +"\n"
				+ "Students Registerd: [" +listOfStudents.inOrder(root) + "]";  
		return toString;
	}
	
	public void setRoot(Node newInsert) {
		root = newInsert;
	}
	
	public void takeSeat() {
			numOfSeatsAvailable--;
	}
	public void releaseSeat() {
		numOfSeatsAvailable++;
	}
	
	
}
