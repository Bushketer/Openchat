#!/usr/bin/env bash

if [[ -d src/ ]]; then
	echo "building src/"
	cd src/
else
	echo "building locally"
fi

javac server.java ServerChatRoom.java
jar cfe server.jar server server.class server\$ConnectionHandler.class ServerChatRoom.class
