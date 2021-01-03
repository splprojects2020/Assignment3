CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

# All Targets
all: bin/cTrace

# Tool invocations
# Executable "cTrace" depends on the files main.o Agent.o Graph.o Tree.o Session.o
bin/cTrace: bin/connectionHandler.o bin/BGRSClient.o bin/MessageEncoderDecoder.o bin/MessageProtocol.o bin/Task.o
	@echo 'Building target: cTrace'
	@echo 'Invoking: C++ Linker'
	g++ -o bin/cTrace bin/connectionHandler.o bin/BGRSClient.o bin/MessageEncoderDecoder.o bin/MessageProtocol.o bin/Task.o
	@echo 'Finished building target: cTrace'
	@echo ' '

#connectionHandler.o Depends on the source and header files src/connectionHandler.cpp include/connectionHandler.h
bin/connectionHandler.o: src/connectionHandler.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/connectionHandler.o src/connectionHandler.cpp 

#BGRSClient.o Depends on the source and header files src/BGRSClient.cpp include/BGRSClient.h
bin/BGRSClientr.o: src/BGRSClient.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/BGRSClient.o src/BGRSClient.cpp 

#MessageEncoderDecoder.o Depends on the source and header files src/MessageEncoderDecoder.cpp include/MessageEncoderDecoder.h
bin/MessageEncoderDecoder.o: src/MessageEncoderDecoder.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/MessageEncoderDecoder.o src/MessageEncoderDecoder.cpp 

#MessageProtocol.o Depends on the source and header files src/MessageProtocol.cpp include/MessageProtocol.h
bin/MessageProtocol.o: src/MessageProtocol.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/MessageProtocol.o src/MessageProtocol.cpp 

#Task.o Depends on the source and header files src/Task.cpp include/Task.h
bin/Task.o: src/MessageProtocol.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/Task.o src/Task.cpp 


#Clean the build directory
clean: 
	rm -f bin/*
