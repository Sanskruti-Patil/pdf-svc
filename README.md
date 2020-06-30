# Template to PDF

The aim of this service is to take a document template as an input, read it and replace the placeholders in the docx file with the data in the database for perticular client or policy the service is getting called. 

## Overview

In this service, we get the clients details by hitting the api with client id. The ClientService is then get called and it calls the template service to replace the placeholders in the document template with data taken from the databse for perticular client.

## How to Start

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
    
## Running the project

If u are using docker, the runscript will automatically start the java application. So like any other rest service we willhit the URL any browser.

1. Open the browser and hit theip address:

    `` 172.17.0.2:8080/client ``
    
    This will return the list of all clients in our database.
    
2. Hit the below address to generate pdf file for specific client using its client number.

    `` 172.17.0.2:8080/client/{cnmbr} ``
    
    This will return a pdf file in the browser giving options to either open the file or to save the file.
    
The ip address to run the API may be different depending upon the cofiguration. 
