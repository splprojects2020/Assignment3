CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

# All Targets
all: bin/cTrace

# Tool invocations
# Executable "cTrace" depends on the files main.o Agent.o Graph.o Tree.o Session.o
bin/cTrace: bin/connectionHandler.o bin/echoClient.o 
	@echo 'Building target: cTrace'
	@echo 'Invoking: C++ Linker'
	g++ -o bin/cTrace bin/connectionHandler.o bin/echoClient.o
	@echo 'Finished building target: cTrace'
	@echo ' '

#connectionHandler.o Depends on the source and header files src/connectionHandler.cpp include/connectionHandler
bin/connectionHandler.o: src/connectionHandler.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/connectionHandler.o src/connectionHandler.cpp 

#echoClient.o Depends on the source and header files src/echoClient.cpp include/connectionHandler.h 
bin/echoClient.o: src/echoClient.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/echoClient.o src/echoClient.cpp


#Clean the build directory
clean: 
	rm -f bin/*
