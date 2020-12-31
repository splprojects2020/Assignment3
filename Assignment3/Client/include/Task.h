
#ifndef ASSIGNMENT3_TASK_H
#define ASSIGNMENT3_TASK_H
#include <iostream>
#include <thread>
#include <string>
#include <connectionHandler.h>

class Task {
public:
    Task();
    virtual void run(ConnectionHandler &connectionHandler)=0;

protected:
    bool terminated;
};
class readFromConsoleTask: public Task{
public:
    readFromConsoleTask();
    virtual void run(ConnectionHandler &connectionHandler);

};
/*class readFromSocketTask: public Task{
    readFromSocketTask();
    virtual void run();
};*/
#endif
