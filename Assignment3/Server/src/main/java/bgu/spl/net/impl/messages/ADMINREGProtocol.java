package bgu.spl.net.impl.messages;
import java.util.Vector;

import bgu.spl.net.api.MessagingProtocol;

public class ADMINREGProtocol implements MessagingProtocol<Vector<String>>{
	
	private boolean shouldTerminate=false;
	private Database database;
	
	public Vector<String> process(Vector<String> msg){
		return null;
	}
	public boolean shouldTerminate() {
		return shouldTerminate;
	}
}
