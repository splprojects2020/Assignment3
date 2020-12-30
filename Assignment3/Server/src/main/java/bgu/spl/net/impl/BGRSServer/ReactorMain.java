package bgu.spl.net.impl.BGRSServer;
import java.util.Vector;
import bgu.spl.net.impl.messages.*;
import bgu.spl.net.srv.*;

public class ReactorMain {

	public static void main(String[] args) {
		System.out.println("ReactorMain");
	
		Server<Vector<String>> reactorServer=Server.reactor(4, 7777, ()->new BGRSProtocol(), ()->new BGRSEncoderDecoder());
		reactorServer.serve();
		
		
	}

}
