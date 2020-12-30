
#ifndef ASSIGNMENT3_MESSAGEENCODERDECODER_H
#define ASSIGNMENT3_MESSAGEENCODERDECODER_H
#include <iostream>
#include <cstring>
#include <unordered_map>
#include <string>
#include <algorithm>

using namespace std;

using std::cin;
using std::cout;
using std::string;

class MessageEncoderDecoder {
public:
    MessageEncoderDecoder();
    char* encode(string &consoleInput);
    string decode();
    char* shortToBytes(short & shortNum);
    short getShortOpCode(string & opCodeString);
};


#endif
