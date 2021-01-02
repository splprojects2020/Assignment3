
#ifndef ASSIGNMENT3_MESSAGEENCODERDECODER_H
#define ASSIGNMENT3_MESSAGEENCODERDECODER_H
#include <iostream>
#include <cstring>
#include <unordered_map>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

using std::cin;
using std::cout;
using std::string;

class MessageEncoderDecoder {
public:
    MessageEncoderDecoder();
    string encode(string &consoleInput);
    string decode(char nextByte);
    void shortToBytes(short num, char* bytesArr);
    short bytesToShort(char bytesArr);
    short getShortOpCode(string & opCodeString);
    int opCounter;
    short opCode;
    int msgOpCode;
    string result;
};


#endif
