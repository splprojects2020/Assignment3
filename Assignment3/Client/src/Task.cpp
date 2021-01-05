
#include "Task.h"

Task::Task(bool &shutdown): encDec(),protocol(),terminated(shutdown){}
void Task::run(ConnectionHandler &connectionHandler) {}
Task::~Task()=default;

//readFromConsoleTask
readFromConsoleTask::readFromConsoleTask(bool &shutdown):Task(shutdown){}
readFromConsoleTask::~readFromConsoleTask()=default;
void readFromConsoleTask::run(ConnectionHandler &connectionHandler) {
    using namespace std;
    while(!terminated) {
        //THREAD 1
        string consoleInput;
        getline(cin, consoleInput);
        string output = encDec.encode(consoleInput);
        if(output!="INPUT ERROR") {//check if the input from the keyboard is valid
            if(!connectionHandler.sendBytes(output.c_str(), output.length()))
                break;
        }
        boost::this_thread::sleep( boost::posix_time::milliseconds(1000) );
    }
    std::this_thread::yield();

}


//readFromSocketTask
readFromSocketTask::readFromSocketTask(bool &shutdown):Task(shutdown){}
readFromSocketTask::~readFromSocketTask()=default;
void readFromSocketTask::run(ConnectionHandler &connectionHandler) {
    while(!terminated) {
        char byte;
        string result = "not-finished";
        while (result == "not-finished") {
            if(!connectionHandler.getBytes(&byte, 1))
                break;
            result = encDec.decode(byte);
        }
        terminated = protocol.process(result);
    }
    std::this_thread::yield();

}
