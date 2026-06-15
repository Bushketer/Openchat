#!/usr/bin/env bash

if [[ -d src/ ]]; then
	echo "building src/"
	cd src/
else
	echo "building locally"
fi

javac server.java ServerChatRoom.java SecretHolder.java Transmission.java Packet*.java Base*.java *crypt*.java
jar cfe server.jar server server.class server\$ConnectionHandler.class ServerChatRoom.class SecretHolder.class Transmission.class Packet*.class Base*.class *cryp*.class
