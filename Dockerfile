# Use the official image as a parent image.
FROM alpine:latest

# Set the working directory.

RUN mkdir /app
WORKDIR /app

#comment out these 2 lines if not on Archlinux
#RUN mv /etc/pacman.conf /etc/pacman.conf.old
#COPY pacman.conf /etc/

RUN apk update && apk add --no-cache wget maven libreoffice-writer openjdk8-jre dash 

RUN apk add --no-cache font-noto ttf-freefont ttf-linux-libertine

RUN pwd
COPY pdfgen ./pdfgen
COPY h2 ./h2
COPY restapi.mv.db .
COPY runproject.sh .

#just to speed up on development machine
COPY m2 /root/.m2

EXPOSE 8080

CMD ["dash","runproject.sh","in-docker"]
