#!/bin/sh
java -cp h2/bin/h2*.jar org.h2.tools.Server -tcp &
sleep 1

mkdir /tmp -p
cp restapi.mv.db /tmp

if [ "$1" = "in-docker" ]; then
    echo "cd into pdfgen"
    cd pdfgen
fi

mvn spring-boot:run
