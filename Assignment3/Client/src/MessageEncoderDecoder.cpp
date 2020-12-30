#include <MessageEncoderDecoder.h>
#include <stdio.h>
MessageEncoderDecoder::MessageEncoderDecoder() {}
char * MessageEncoderDecoder::encode(string &consoleInput) {
    char* result;
    string opCodeString;
    for(int i=0;i<consoleInput.length() && consoleInput[i]!=' ';i++) { //return the command
        opCodeString += consoleInput[i];
    }

    short opCodeAsShort = getShortOpCode(opCodeString);
    char* opCodeAsBytes = shortToBytes(opCodeAsShort);
    strcpy (result,opCodeAsBytes); //copies opCode to result
    string subInput = consoleInput.substr(consoleInput.find(' ')+1);

    if(opCodeAsShort>=1 && opCodeAsShort<=3 | opCodeAsShort==8){ //ADMINREG, STUDENTREG, LOGIN, STUDENTSTAT
        if(opCodeAsShort!=8)
            std::replace(subInput.begin(),subInput.end(),' ','\0');
        subInput+='\0';
        strcat (result,subInput.c_str()); //appends subInput to result
    }
    if(opCodeAsShort>=5 && opCodeAsShort<=7 | opCodeAsShort==9 | opCodeAsShort==10){ //COURSEREG or KDAMCHECK or COURSESTAT or ISREGISTERED or UNREGISTER
        short courseNumToShort = stoi(subInput);
        char * courseNumAsBytes = shortToBytes(courseNumToShort);
        strcat(result,courseNumAsBytes); //appends subInput to result
    }
    return result;
}

char * MessageEncoderDecoder::shortToBytes(short &shortNum) {
    char shortAsBytes [2];
    //opCode in 2 bytes arr
    shortAsBytes [0] =(char)((shortNum >> 8) & 0xFF);
    shortAsBytes [1] = (char)(shortNum & 0xFF);
    return shortAsBytes;
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