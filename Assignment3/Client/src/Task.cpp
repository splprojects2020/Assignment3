
#include "Task.h"
Task::Task(): terminated(false){}
void Task::run() {}

readFromConsoleTask::readFromConsoleTask() = default;
void readFromConsoleTask::run() {
    using namespace std;
    while(!terminated) {
        string input;
        cout << "CLIENT#1>";
        getline(cin, input);
        cout << "CLIENT#1<" << input << ".\n";
        if(input=="close")
            terminated=true;
    }
    std::this_thread::yield();
}