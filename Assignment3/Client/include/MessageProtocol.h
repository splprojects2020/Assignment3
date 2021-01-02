//
// Created by spl211 on 1/2/21.
//

#ifndef ASSIGNMENT3_MESSAGEPROTOCOL_H
#define ASSIGNMENT3_MESSAGEPROTOCOL_H
#include <iostream>
#include <string>

using namespace std;

using std::cin;
using std::cout;
using std::string;

class MessageProtocol {
public:
    MessageProtocol();
    bool process(string &msg);
};
#endif //ASSIGNMENT3_MESSAGEPROTOCOL_H
