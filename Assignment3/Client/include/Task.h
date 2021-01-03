
#ifndef ASSIGNMENT3_TASK_H
#define ASSIGNMENT3_TASK_H
#include <iostream>
 #include <thread>
#include <string>
#include <connectionHandler.h>
#include <MessageProtocol.h>
#include <MessageEncoderDecoder.h>

class Task {
public:
    Task(bool &shutdown);
    virtual void run(ConnectionHandler &connectionHandler)=0;

protected:
    MessageEncoderDecoder encDec;
    MessageProtocol protocol;
    bool &terminated;

};
class readFromConsoleTask: public Task{
public:
    readFromConsoleTask(bool &shutdown);
    virtual void run(ConnectionHandler &connectionHandler);


};
class readFromSocketTask: public Task{
public:
    readFromSocketTask(bool &shutdown);
    virtual void run(ConnectionHandler &connectionHandler);


};
#endif
