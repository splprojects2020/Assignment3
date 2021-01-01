package bgu.spl.net.impl.messages;

import java.util.Arrays;
import java.util.Vector;
import bgu.spl.net.api.*;

public class BGRSEncoderDecoder implements MessageEncoderDecoder<Vector<String>>{
	private int opCounter=2;
	private int courseCounter=2;
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
    	if(opCounter==0) {
    		opCode=bytesToShort(opArr); //returns opCode
    		msg.add(String.valueOf(opCode));
    		opCounter=-1;
    		System.out.println(opCode);
       	 if(opCode==4 || opCode==11) //LOGOUT or MYCOURSES	**WORKING**
     		finished=true;
    	}
    	else {
    	 if(opCode>=1 && opCode<=3){//ADMINREG or STUDENTREG or LOGIN **WORKING**
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
    	
    	 if(opCode>=5 && opCode<=7 || opCode==9 || opCode==10) {//COURSEREG or KDAMCHECK or COURSESTAT or ISREGISTERED or UNREGISTER **WORKING**
        	if(courseCounter>0) {
        		opArr[2-courseCounter--]=nextByte;
        	}
        	 if(courseCounter==0) {
        		short courseNum=bytesToShort(opArr); //returns courseNum
        		msg.add(String.valueOf(courseNum));
        		finished=true;
        	}
    	}
    	 if(opCode==8) {//STUDENTSTAT **WORKING**
    		if(nextByte=='\0') {
    			msg.add(popString());
    			finished=true;
    		}
    		else {
    			pushByte(nextByte);
    		}
    	}
    	}	
    	if(finished) {
    		try {
    			Vector<String> result = (Vector<String>)msg.clone();
    			return result;
    			}
    		finally {reset();}
    	}
    	return null;
   
   	}


    public byte[] encode(Vector<String> message) {
    	short opCode=-1;
    	short messageOpCode=-1;
     	if(!message.isEmpty()) {
     		opCode=Short.parseShort(message.get(0));
     		messageOpCode=Short.parseShort(message.get(1));
     		}
    	byte[] output=shortToBytes(opCode,messageOpCode);
		
		if(message.size()<3) { //ERROR or no additional information for ACK
			return output;
		}
		//adds additional information for ACK
		String optional=message.get(2);
		byte []optionalByte=optional.getBytes();
		
		return mergeBytes(output,optionalByte);
		      	    		  
    }
    
    private byte[]mergeBytes(byte[] arr1,byte[] arr2){
    	
    	byte []merged=new byte[arr1.length+arr2.length];
		for(int i=0;i<merged.length;i++) {
			if(i<arr1.length) {
				merged[i]=arr1[i];
			}
			else {
				merged[i]=arr2[i-arr1.length];
			}
		}
		return merged;	
    }
    
    
    public byte[] shortToBytes(short num) {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
    
    public byte[] shortToBytes(short num1,short num2) {
        byte[] bytesArr = new byte[4];
        bytesArr[0] = (byte)((num1 >> 8) & 0xFF);
        bytesArr[1] = (byte)(num1 & 0xFF);
        bytesArr[2] = (byte)((num2 >> 8) & 0xFF);
        bytesArr[3] = (byte)(num2 & 0xFF);
       
        return bytesArr;
    }
    
    public short bytesToShort(byte[] byteArr) {
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
    	courseCounter=2;
    	opCode=-1;
    	isDone=false;
    	finished=false;
    	msg.clear();
    	System.out.print("cleared");
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