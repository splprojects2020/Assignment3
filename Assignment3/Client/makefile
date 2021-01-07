CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

# All Targets
all: bin/BGRSclient

# Tool invocations
# Executable "BGRSclient" depends on the files connectionHandler.o Main.o MessageEncoderDecoder.o MessageProtocol.o Task.o
bin/BGRSclient: bin/connectionHandler.o bin/Main.o bin/MessageEncoderDecoder.o bin/MessageProtocol.o bin/Task.o
	@echo 'Building target: BGRSclient'
	@echo 'Invoking: C++ Linker'
	g++ -o bin/BGRSclient bin/connectionHandler.o bin/Main.o bin/MessageEncoderDecoder.o bin/MessageProtocol.o bin/Task.o -lboost_system -lpthread -lboost_thread
	@echo 'Finished building target: BGRSclient'
	@echo ' '

#connectionHandler.o Depends on the source and header files src/connectionHandler.cpp include/connectionHandler.h
bin/connectionHandler.o: src/connectionHandler.cpp
	g++  -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/connectionHandler.o src/connectionHandler.cpp

#Main.o Depends on the source and header files src/Main.cpp
bin/Main.o: src/Main.cpp
	g++  -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/Main.o src/Main.cpp

#MessageEncoderDecoder.o Depends on the source and header files src/MessageEncoderDecoder.cpp include/MessageEncoderDecoder.h
bin/MessageEncoderDecoder.o: src/MessageEncoderDecoder.cpp
	g++  -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/MessageEncoderDecoder.o src/MessageEncoderDecoder.cpp

#MessageProtocol.o Depends on the source and header files src/MessageProtocol.cpp include/MessageProtocol.h
bin/MessageProtocol.o: src/MessageProtocol.cpp
	g++  -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/MessageProtocol.o src/MessageProtocol.cpp

#Task.o Depends on the source and header files src/Task.cpp include/Task.h
bin/Task.o: src/MessageProtocol.cpp
	g++   -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/Task.o src/Task.cpp


#Clean the build directory
clean: 
	rm -f bin/*
