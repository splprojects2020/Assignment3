#include <stdlib.h>
#include <connectionHandler.h>
#include <Task.h>


/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
    std::string host = argv[1];
    short port = atoi(argv[2]);

    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

    bool shutDown=false;

    readFromConsoleTask* readFromConsoleTask1= new readFromConsoleTask(shutDown);
    readFromSocketTask* readFromSocketTask1= new readFromSocketTask(shutDown);

    std::thread th1(&readFromConsoleTask::run,readFromConsoleTask1,std::ref(connectionHandler));
    std::thread th2(&readFromSocketTask::run,readFromSocketTask1,std::ref(connectionHandler));

    th2.join();
    th1.detach();

    delete readFromConsoleTask1;
    delete readFromSocketTask1;
    return 0;
}
