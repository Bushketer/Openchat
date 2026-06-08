#!/usr/bin/env bash

if [[ -d src/ ]]; then
        echo "building src/"
        cd src/
else
        echo "building locally"
fi

javac client.java Utils.java SecretHolder.java Packet.java Base64*.java Transmission.java *crypt*.java
jar cfe client.jar client client.class client\$InputHandler.class Utils.class SecretHolder.class Packet.class Base64*.class Transmission.class *crypt*.class













