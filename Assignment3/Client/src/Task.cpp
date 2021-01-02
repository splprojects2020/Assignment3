#include <MessageEncoderDecoder.h>
#include "Task.h"

Task::Task(): terminated(false){}
void Task::run(ConnectionHandler &connectionHandler) {}

readFromConsoleTask::readFromConsoleTask() = default;
void readFromConsoleTask::run(ConnectionHandler &connectionHandler) {
    using namespace std;
    MessageEncoderDecoder encDec;
    while(!terminated) {
        string consoleInput;
        cout << "CLIENT#1>";
        getline(cin, consoleInput);
        string output = encDec.encode(consoleInput);
        connectionHandler.sendBytes(output.c_str(),output.length());
        char byte;
        string result = "not-finished";
        while(result=="not-finished"){
            connectionHandler.getBytes(&byte,1);
            cout<< byte;
            result = encDec.decode(byte);
        }
        for(int i=0;i<result.length();i++){
            cout << result[i];
        }
        // if(input=="close")
        //terminated=true;
    }
    std::this_thread::yield();

}