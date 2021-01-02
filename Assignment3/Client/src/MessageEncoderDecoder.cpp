#include <MessageEncoderDecoder.h>
#include <stdio.h>
MessageEncoderDecoder::MessageEncoderDecoder():opCounter(4),opCode(-1),msgOpCode(-1) {}
string MessageEncoderDecoder::encode(string &consoleInput) {
    string result;
    string opCodeString="";

    for(int i=0;i<consoleInput.length() && consoleInput[i]!=' ';i++) { //return the command
        opCodeString += consoleInput[i];
    }
    short opCodeAsShort = getShortOpCode(opCodeString);
    char opCodeAsBytes[2];

    shortToBytes(opCodeAsShort,opCodeAsBytes);

    result+=opCodeAsBytes[0];
    result+=opCodeAsBytes[1];


    string subInput = consoleInput.substr(consoleInput.find(' ')+1);

    if(opCodeAsShort>=1 && opCodeAsShort<=3 | opCodeAsShort==8){ //ADMINREG, STUDENTREG, LOGIN, STUDENTSTAT
        if(opCodeAsShort!=8)
            std::replace(subInput.begin(),subInput.end(),' ','\0');
        subInput+='\0';
        result.append(subInput); //appends subInput to result
    }
    if(opCodeAsShort>=5 && opCodeAsShort<=7 | opCodeAsShort==9 | opCodeAsShort==10){ //COURSEREG or KDAMCHECK or COURSESTAT or ISREGISTERED or UNREGISTER
        short courseNumToShort = stoi(subInput);
        char  courseNumAsBytes[2];
        shortToBytes(courseNumToShort,courseNumAsBytes);
        result+=courseNumAsBytes[0];
        result+=courseNumAsBytes[1];
        // result.append(courseNumAsBytes); //appends subInput to result
    }
    return result;
}

string MessageEncoderDecoder::decode(char nextByte) {
    if(opCounter>0) {
        result += nextByte;
        opCounter--;
    }
    if(opCounter==0){
        opCounter=-1;
        if(result.substr(0,1)=="13" || result.substr(2,3)>="01" & result.substr(2,3)<="05"
            | result.substr(2,3)=="10")//ERROR 1-5 10
            return result;
        }
    else {
       if(nextByte==0) return result;
       result+=nextByte;
    }
    return "not-finished";
}


void MessageEncoderDecoder::shortToBytes(short num, char* bytesArr){
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}
short bytesToShort(char* bytesArr)
{
    auto result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}
short MessageEncoderDecoder::getShortOpCode(string &opCodeString) {
    unordered_map<string, short> commands;
    commands["ADMINREG"]=1;
    commands["STUDENTREG"]=2;
    commands["LOGIN"]=3;
    commands["LOGOUT"]=4;
    commands["COURSEREG"]=5;
    commands["KDAMCHECK"]=6;
    commands["COURSESTAT"]=7;
    commands["STUDENTSTAT"]=8;
    commands["ISREGISTERED"]=9;
    commands["UNREGISTER"]=10;
    commands["MYCOURSES"]=11;
    commands["ACK"]=12;
    commands["ERR"]=13;
    return commands[opCodeString];
}