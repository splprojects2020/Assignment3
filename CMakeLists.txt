cmake_minimum_required(VERSION 3.14)
project(Assignment3)

set(CMAKE_CXX_STANDARD 14)

include_directories(Assignment3/Client/include)
link_libraries("-lboost_system")
link_libraries(-pthread)
add_executable(Assignment3
            Assignment3/Client/src/connectionHandler.cpp
            Assignment3/Client/src/echoClient.cpp Assignment3/Client/src/BGRSClient.cpp Assignment3/Client/src/Task.cpp Assignment3/Client/src/MessageEncoderDecoder.cpp Assignment3/Client/include/MessageEncoderDecoder.h)

