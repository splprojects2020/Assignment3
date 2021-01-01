#include <stdlib.h>
#include <connectionHandler.h>
#include <Task.h>
#include <thread>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
    std::string host = "127.0.0.1";
    short port = 7777;//atoi(argv[2]);

    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }


    readFromConsoleTask readFromConsoleTask;
    readFromConsoleTask.run(connectionHandler);
    //std::thread th1(&readFromConsoleTask::run,&readFromConsoleTask);
    //th1.join();
    return 0;
}