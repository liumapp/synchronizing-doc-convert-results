# synchronizing-doc-convert-results

Multy upload and conversion of doc documents to PDF files. Frontend synchronous acquisition of conversion results and download.

[中文文档](https://github.com/liumapp/synchronizing-doc-convert-results/blob/master/README_CN.md) | [English Docment](https://github.com/liumapp/simple-sdk-example/blob/master/README.md)

* [functional introduction](#functional-introduction)
* [how to use](#how-to-use)
	* [Docker](#docker)
		* [installation images](#installation-images)
		* [starting container](#starting-container)
		* [stoping container](#stoping-container)
		* [remove images](#remove-images)
	* [IDEA](#idea)

## functional introduction

* users upload one or more doc/docx documents.

* the backend executes the conversion of documents asynchronously, making use of the rabbitMq queue.

* the frontend can synchronously acquire the conversion results and provide downloading operations in tabular form.

the moving picture is introduced as follows:

![ui-show.gif](https://github.com/liumapp/synchronizing-doc-convert-results/blob/master/pic/ui-show.gif)

* If the user uploads a problematic doc document, for example, we change the suffix name of an JPG picture to '.doc' and uploaded it, then the backend can capture the exception in the process of conversion to the document and feed it back to the front end.

the moving picture is introduced as follows:

![ui-with-wrong-show.git](https://github.com/liumapp/synchronizing-doc-convert-results/blob/master/pic/ui-with-wrong-show.gif)

## how to use

### Docker

first of all, make sure that your local system has support from docker, docker-compose, and Maven.

secondly, make sure that you have not modified the synch-service's project configuration file, and if modified, make sure that the value of the spring.profiles.active is 'docker', and the connection information to the synch-rabbitmq container is consistent with the docker-compose.yml.

#### installation images

after copying the project to the locality, execute the script named build-image.sh to install the images.

the installation of the images is driven by docker-maven-plugin, so you need to ensure that your machine provides maven3 before executing the script.

#### starting container

use docker-compose to execute commands:

	docker-compose up -d

to start the container

after the container is activated, you can use kitematic and other tools to see the running effect.

it should be looked like this:

![docker-show.jpg](https://github.com/liumapp/synchronizing-doc-convert-results/blob/master/pic/docker-show.jpg)

the need to use docker-compose for container choreography is because the system relies on rabbitMQ support.

the existence of rabbitMQ itself is running in an independent container, and by configuring docker-compose, the two containers shared a gateway to interact with each other.

the gateway configuration is as follows:

	networks:
	  synchronizing-doc-convert-results:
	    driver: bridge

and in the configuration of two containers, add the following:

	networks:
	  - synchronizing-doc-convert-results

#### stoping container

use docker-compose to execute commands:

	docker-compose down

to stop the container

it should be noted that stoping the container itself will not remove images.

#### remove images

in the project root directory, execute the script 'rm-image.sh' to delete the images file. Note that the execution of the operation is that the container is in a stop state.

### IDEA

Considering that some users may not know much about docker, this project can also be run in a traditional way.

the project itself uses the form of frontend and backend separation mode, the frontend project is synch-ui, using vue2.0 framework. the backend project is synch-service, using springboot1.5.6 framework. 

so, if you want to run both frontend and backend projects at the same time, your system will needs both java and nodejs support.

but considering that most developers do not choose the full stack as their own development discovery, the project provides some convenience for pure java developers.

synch-service uses thymeleaf to load static files coming from synch-ui compiled and already completed the configuration, so pure java developers only need to import synch-service into IDEA(import process omission), and update application.yml to set spring.profiles.active=dev, and update liumapp.filemanager.savepath=${absolute_path_of_data} and access to http://localhost:2020 after startup to see system effects.

at the same time, if you are a pure frontend developer, and you already open the synch-ui project in webstorm and make use of it by running:

	npm run dev

you will need to start the backend project at the same time to see the system effect in http://localhost:8080.

if you have made some modifications to the frontend code and want to import the latest effect into synch-service, then execute the script update-ui.sh to automatically complete the import of the synch-ui compiled file.	












