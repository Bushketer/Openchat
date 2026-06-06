#!/usr/bin/env bash

if [[ -d src/ ]]; then
        echo "building src/"
        cd src/
else
        echo "building locally"
fi

javac client.java
jar cfe client.jar client client.class client\$InputHandler.class













