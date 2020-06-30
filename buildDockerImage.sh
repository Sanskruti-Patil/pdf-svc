#!/bin/sh

#EXEDIR=pwd
#PROJECTDIR=dirname "$(readlink -f "$0")"
#cd $PROJECTDIR

h2=h2-2019-10-14.zip
if [ ! -d h2 ]; then
	echo "Downloding $h2"
	wget "https://h2database.com/h2-2019-10-14.zip"
	unzip h2-2019-10-14.zip
fi

#comment out for production
m2=m2
if [ ! -d m2 ]; then
    cp ~/.m2 m2 -r
fi

rm -rf pdfgen
mkdir pdfgen

mvn clean install spring-boot:repackage
cp pom.xml target ClientTemplate.docx  -t pdfgen -r


docker build --tag pdfgen:0.1 .

#cd $EXEDIR
