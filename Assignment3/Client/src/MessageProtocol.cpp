//
// Created by spl211 on 1/2/21.
//

#include "MessageProtocol.h"


MessageProtocol::MessageProtocol(){}

bool MessageProtocol::process(string &msg) {

    if(msg.substr(0,2)=="13"){
        cout<<"ERROR "<<msg.substr(2)<<"\n";
    }
    else if(msg.substr(0,2)=="12"){
        if(msg.length()<=4){
            cout<<"ACK "<<msg.substr(2)<<"\n";
            if(msg.substr(2)=="4"){
                return true;
            }
        }
        else{
           cout<<"ACK "<<msg.substr(2)<<"\n";
        }
    }
    return false;
}