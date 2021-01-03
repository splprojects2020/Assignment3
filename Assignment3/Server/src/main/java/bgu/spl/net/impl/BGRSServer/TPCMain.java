package bgu.spl.net.impl.BGRSServer;

import java.util.Vector;

import bgu.spl.net.impl.messages.BGRSEncoderDecoder;
import bgu.spl.net.impl.messages.BGRSProtocol;
import bgu.spl.net.resources.Database;
import bgu.spl.net.srv.Server;

public class TPCMain {

	public static void main(String[] args) {
		
		Database database = Database.getInstance();
		if(!database.initialize("./Courses.txt"))
			return;
		Server<Vector<String>> tpcServer=Server.threadPerClient(7777, ()->new BGRSProtocol(), ()->new BGRSEncoderDecoder());
		tpcServer.serve();

	}

}
