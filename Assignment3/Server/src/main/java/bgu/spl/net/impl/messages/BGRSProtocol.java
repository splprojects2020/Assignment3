package bgu.spl.net.impl.messages;

import java.util.Vector;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.resources.*;

public class BGRSProtocol implements MessagingProtocol<Vector<String>>{
	
	private boolean shouldTerminate=false;
	private Database database= Database.getInstance();
	
	public Vector<String> process(Vector<String> msg){
		int opCode = Integer.parseInt(msg.get(0));
		Vector<String> output = new Vector<String>();
		Boolean failed=false;
		switch(opCode) {
		case 1:
			if(database.register(msg.get(1), msg.get(2),true)) {//return ACK
				output.add("12");
				output.add("1");//
				return output;
			}
			failed=true;
		case 2:
			if(database.register(msg.get(1), msg.get(2),false)) {//return ACK
				output.add("12");
				output.add("2");
				return output;
			}
			failed=true;
		case 3:
			if(database.login(msg.get(1), msg.get(2))) {//return ACK
				output.add("12");
				output.add("3");
				return output;
			}	
			failed=true;
		}
		if(failed) {//return Error
			
		}
		return null;
	}
	
	public boolean shouldTerminate() {
		return shouldTerminate;
	}
}
