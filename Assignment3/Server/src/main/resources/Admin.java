
public class Admin extends User{
	
	public Admin(String userName,String password) {
		super(userName,password);
	}
	
	public String courseStatus(int courseNum) {
		return database.courseStatus(courseNum);
	}
	public String studentStatus(String studentName) {
		return database.studentStatus(studentName);
	}

}
