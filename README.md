# Template to PDF

The aim of this service is to take a document template as an input, read it and replace the placeholders in the docx file with the data in the database for perticular client or policy the service is getting called. 

## Overview

In this service, we get the clients details by hitting the api with client id. The ClientService is then get called and it calls the template service to replace the placeholders in the document template with data taken from the databse for perticular client.

## How to run

1. You can just run the project using the shell script

    `` ./runproject.sh ``
    
OR

1. Start the Server 

    `` java -cp h2/bin/h2*.jar org.h2.tools.Server -tcp ``

2. Place test database in tmpfs

    `` cp restapi.mv.db /tmp ``

3. Run Project

    `` mvn spring-boot:run ``

## You can also use docker
1. Start the docker 

    `` sudo systemctl start docker ``
    
2. Run the docker build image of our project

    `` ./buildDockerImage.sh ``
    
3. Run the docker image of our project

    `` docker run pdfgen:0.1 ``
