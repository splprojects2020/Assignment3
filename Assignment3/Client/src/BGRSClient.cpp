#include <stdlib.h>
#include <connectionHandler.h>
#include <Task.h>
#include <thread>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
    readFromConsoleTask readFromConsoleTask;
    std::thread th1(&readFromConsoleTask::run,&readFromConsoleTask);
    th1.join();
    return 0;
}