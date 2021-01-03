
#include "Task.h"

Task::Task(bool &shutdown): terminated(shutdown){}
void Task::run(ConnectionHandler &connectionHandler) {}

//readFromConsoleTask
readFromConsoleTask::readFromConsoleTask(bool &shutdown):Task(shutdown){}
void readFromConsoleTask::run(ConnectionHandler &connectionHandler) {
    using namespace std;
    while(!terminated) {
        //THREAD 1
        string consoleInput;
        getline(cin, consoleInput);
        string output = encDec.encode(consoleInput);
        if(output!="INPUT ERROR") {//check if the input from the keyboard is valid
            connectionHandler.sendBytes(output.c_str(), output.length());
        }
    }
    std::this_thread::yield();

}

//readFromSocketTask
readFromSocketTask::readFromSocketTask(bool &shutdown):Task(shutdown){}

void readFromSocketTask::run(ConnectionHandler &connectionHandler) {
    while(!terminated) {
        char byte;
        string result = "not-finished";
        while (result == "not-finished") {
            connectionHandler.getBytes(&byte, 1);
            result = encDec.decode(byte);
        }
        terminated = protocol.process(result);
        if(terminated){
            connectionHandler.close();
        }
    }
    std::this_thread::yield();

}