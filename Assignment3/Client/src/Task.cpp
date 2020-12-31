#include <MessageEncoderDecoder.h>

#include "Task.h"
Task::Task(): terminated(false){}
void Task::run(ConnectionHandler &connectionHandler) {}

readFromConsoleTask::readFromConsoleTask() = default;
void readFromConsoleTask::run(ConnectionHandler &connectionHandler) {
    using namespace std;
    MessageEncoderDecoder encDec;
   while(!terminated) {
        string input;
        cout << "CLIENT#1>";
        getline(cin, input);
        connectionHandler.sendBytes(input.c_str(),input.length());

       /*string input = "LOGIN AMIT IMGAT69";
       string final=encDec.encode(input);
       const char* finalCountDown=final.c_str();

       cout << "CLIENT#1<";

       for(int i=0;i<final.length();i++){
           cout << finalCountDown[i];
       }
       char amitEfes=finalCountDown[final.length()-1];
   cout << "\n" <<(int)amitEfes;
   cout << "CLIENT#1<" << (int)final[0]<<(int)final[1] <<final<< "\n";
   */
      // if(input=="close")
           //terminated=true;
   }
   std::this_thread::yield();

}