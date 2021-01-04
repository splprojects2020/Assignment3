package bgu.spl.net.impl.BGRSServer;
import java.util.Vector;
import bgu.spl.net.impl.messages.*;
import bgu.spl.net.srv.*;
import bgu.spl.net.resources.Course;
import bgu.spl.net.resources.Database;

public class ReactorMain {

	public static void main(String[] args) {
		
		Database database = Database.getInstance();
		if(!database.initialize("./Courses.txt"))
			return;
	
		Server<Vector<String>> reactorServer=Server.reactor(Integer.parseInt(args[1]), Integer.parseInt(args[0]), ()->new BGRSProtocol(), ()->new BGRSEncoderDecoder());
		reactorServer.serve();
		
		
	}

}
