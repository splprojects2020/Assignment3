package bgu.spl.net.impl.messages;

import java.util.Vector;
import bgu.spl.net.api.*;

public class BGRSEncoderDecoder implements MessageEncoderDecoder<Vector<String>>{
	private int opCounter=2;
	private short opCode;
	private byte[] bytes = new byte[1 << 10];
	private byte[] byteArr = new byte[2];
    private int len = 0;
    
    public Vector<String> decodeNextByte(byte nextByte){
    	
    	if(opCounter>0) {
    		byteArr[2-opCounter--]=nextByte;
    	}
    	if(opCounter==0)
    		opCode=bytesToShort(byteArr); //returns opCode
    	
    	return null;
    }


    public byte[] encode(Vector<String> message) {
    	
    	
    	
    	return null;
    }
    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }
}
