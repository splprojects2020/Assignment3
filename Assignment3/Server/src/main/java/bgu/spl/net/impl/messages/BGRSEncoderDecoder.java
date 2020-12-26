package bgu.spl.net.impl.messages;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Vector;
import bgu.spl.net.api.*;

public class BGRSEncoderDecoder implements MessageEncoderDecoder<Vector<String>>{
	private int opCounter=2;
	private short opCode=-1;
	private byte[] bytes = new byte[1 << 10];
	private byte[] opArr = new byte[2];
    private int len = 0;
    private boolean isDone = false;
    private boolean finished=false;
    private Vector<String> msg=new Vector<String>();
    
    public Vector<String> decodeNextByte(byte nextByte){
    	
    	if(opCounter>0) {
    		opArr[2-opCounter--]=nextByte;
    	}
    	else if(opCounter==0) {
    		opCode=bytesToShort(opArr); //returns opCode
    		msg.add(String.valueOf(opCode));
    	}
    	
    	
    	else if(opCode>=1 && opCode<=3){//ADMINREG or STUDENTREG or LOGIN
    		if(nextByte=='\0') {
    			msg.add(popString());
    			if(isDone) {
    				finished=true;
    			}
    			isDone=true;
    		}
    		else {
    			pushByte(nextByte);
    		}
    	}
    	else if(opCode==4 || opCode==11) {//LOGOUT or MYCOURSES	
    		finished=true;
    	}
    	else if(opCode>=5 && opCode<=7 || opCode==9 || opCode==10) {//COURSEREG or KDAMCHECK or COURSESTAT or ISREGISTERED or UNREGISTER	
    		opCounter=2;
        	if(opCounter>0) {
        		opArr[2-opCounter--]=nextByte;
        	}
        	else if(opCounter==0) {
        		short courseNum=bytesToShort(opArr); //returns courseNum
        		msg.add(String.valueOf(courseNum));
        		finished=true;
        	}
    	}
    	else if(opCode==8) {//STUDENTSTAT
    		if(nextByte=='\0') {
    			msg.add(popString());
    			finished=true;
    		}
    		else {
    			pushByte(nextByte);
    		}
    	}
    	if(finished) {
    		try {return msg;}
    		finally {reset();}
    	}
  	
    	return null;
    }


    public byte[] encode(Vector<String> message) {
    	String toString="";
    	for(int i=0;i<message.size()-1;i++)
    		toString = toString + message.get(i);
    	  return toString.getBytes();
    }
    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }
    
    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }
    
    private void reset() {
    	opCounter=2;
    	isDone=false;
    	finished=false;
    	msg.clear();
    }
    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len=0;
        bytes= new byte[1 << 10];
        return result;
    }
}