package bgu.spl.net.impl.messages;

import java.util.Vector;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.resources.*;

public class BGRSProtocol implements MessagingProtocol<Vector<String>>{
	
	private boolean shouldTerminate=false;
	private Database database= Database.getInstance();
	
	public Vector<String> process(Vector<String> msg){
		int opCode = Integer.parseInt(msg.get(0));
		
		return null;
	}
	
	public boolean shouldTerminate() {
		return shouldTerminate;
	}
}
