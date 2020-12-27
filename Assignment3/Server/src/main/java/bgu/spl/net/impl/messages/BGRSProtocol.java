package bgu.spl.net.impl.messages;

import java.util.Vector;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.resources.*;

public class BGRSProtocol implements MessagingProtocol<Vector<String>>{
	
	private boolean shouldTerminate=false;
	private Database database= Database.getInstance();
	private User loggedUser=null;
	public Vector<String> process(Vector<String> msg){
		Integer opCode = Integer.parseInt(msg.get(0));
		Vector<String> output = new Vector<String>();
		
		Boolean failed=false;
		switch(opCode) {
		case 1://Admin register
			if(database.register(msg.get(1), msg.get(2),true)) {//return ACK			
				output.add("12");
				output.add("1");//
				return output;
			}
			failed=true;
		case 2://Student register
			if(database.register(msg.get(1), msg.get(2),false)) {//return ACK
				output.add("12");
				output.add("2");
				return output;
			}
			failed=true;
		case 3://Login 
			if(database.login(msg.get(1), msg.get(2))) {//return ACK	
				loggedUser=database.getUser(msg.get(1)); 
				output.add("12");
				output.add("3");
			
				return output;
			}	
			failed=true;
		case 4://Logout 
			if(database.logout(loggedUser)) {//return ACK
				output.add("12");
				output.add("4");
				return output;
			}
			failed=true;	
		
		case 5://Course Register
			if(loggedUser!=null && loggedUser instanceof Student) {
				if(((Student)loggedUser).registerCourse(Integer.parseInt(msg.get(1)))) {
					output.add("12");
					output.add("5");
					return output;
				}
			}
			failed=true;	
		case 6://Kdam Check
			if(loggedUser!=null) {
				output.add("12");
				output.add("6");
				output.add(database.kdamCheck(Integer.parseInt(msg.get(1))));
				return output;
			}
			failed=true;	
		case 7://Course Status
			if(loggedUser!=null && loggedUser instanceof Admin) {
				String status=((Admin)loggedUser).courseStatus(Integer.parseInt(msg.get(1)));
				if(!(status.equals("not-valid"))){
					output.add("12");
					output.add("7");
					output.add(status);
					return output;
				}
				failed=true;
			}
		case 8://Student Status
			if(loggedUser!=null && loggedUser instanceof Admin) {
				String status=((Admin)loggedUser).studentStatus(msg.get(1));
				if(!(status.equals("not-valid"))){
					output.add("12");
					output.add("8");
					output.add(status);
					return output;
				}
				failed=true;
			}
		case 9://Is Registered
			if(loggedUser!=null && loggedUser instanceof Student) {
				String status=((Student)loggedUser).isRegisterdToCourse(Integer.parseInt(msg.get(1)));				
				output.add("12");
				output.add("9");
				output.add(status);
				return output;		
			}
			failed=true;
		case 10://Unregister Course
			if(loggedUser!=null && loggedUser instanceof Student) {
				if(((Student)loggedUser).unregister(Integer.parseInt(msg.get(1))));
				output.add("12");
				output.add("10");
				return output;		
			}
			failed=true;
			
		case 11:
			if(loggedUser!=null && loggedUser instanceof Student) {
				String status=	((Student)loggedUser).getMyCourses();
				output.add("12");
				output.add("11");
				output.add(status);
				return output;		
			}
			failed=true;	
		}
		if(failed) {//return Error
			output.add("13");
			output.add(opCode.toString());
		}
			
		
		return output;
	}
	
	public boolean shouldTerminate() {
		return shouldTerminate;
	}
}
