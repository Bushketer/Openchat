#!/usr/bin/env bash

if [[ -d src/ ]]; then
        echo "building src/"
        cd src/
else
        echo "building locally"
fi

javac client.java SecretHolder.java Packet*.java Base*.java Transmission.java *crypt*.java Terminal.java *Font*.java
jar cfe client.jar client client.class client\$InputHandler.class SecretHolder.class Packet*.class Base*.class Transmission.class *crypt*.class Terminal.class *Font*.class













