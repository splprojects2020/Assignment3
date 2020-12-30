#include <MessageEncoderDecoder.h>
#include "Task.h"
Task::Task(): terminated(false){}
void Task::run() {}

readFromConsoleTask::readFromConsoleTask() = default;
void readFromConsoleTask::run() {
    using namespace std;
    MessageEncoderDecoder encDec;
   /* while(!terminated) {
        string input;
        cout << "CLIENT#1>";
        getline(cin, input);
        */string input = "LOGOUT";
        cout << "CLIENT#1<" << encDec.encode(input) << ".\n";
       // if(input=="close")
            //terminated=true;
   // }
    //std::this_thread::yield();
}