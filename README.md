[![Build Status](https://travis-ci.org/SWEightgroup/Development.svg?branch=master)](https://travis-ci.org/SWEightgroup/Development)
<p aling="center">
<img src="https://raw.githubusercontent.com/SWEightgroup/Colletta/master/RA/template/img/logoSWEight.png" height="200px">
</p>


# Colletta

Colletta is an online platform which allows users to do grammar analysis exercises and get a feedback from the system.

This software is realeased under MIT license.

## Minimum requirements
### Windows
* CPU: Intel X86 family;
* RAM: at least 2GB of RAM;
* Disk's space: at least 1GB;
* Operating system: Windows 7 or superior, 32-bit or 64-bit versions;
* Java: Java SE Development Kit 8;
* Node.js: Node.js 10.15.1;
* Maven: Maven 3.6.0;
* Docker: Docker at least 18.09.6;  
* Browser: Any browser which supports Javascript, HTML5 and CSS3.

### Ubuntu
* CPU: Intel X86 family;
* RAM: at least 2GB of RAM;
* Disk's space: at least 1GB;
* Java: OpenJDK 8 / Oracle JDK 8;
* Node.js: Node.js 10.15.1;
* Maven: Maven 3.6.0;
* Docker: Docker at least 18.09.6; 
* Browser: Any browser which supports Javascript, HTML5 and CSS3.

### MacOS
* Mac Model: all the models sold from 2011 onwards;
* RAM: at least 2GB of RAM;
* Disk’s space: at least 1GB;
* Operating system: OS X 10.10 Yosemite.
* Java: OpenJDK 8 / Oracle JDK 8;
* Node.js: Node.js 10.15.1;
* Maven: Maven 3.6.0;
* Maven: Maven 3.6.0;
* Docker: Docker at least 18.09.6;  
* Browser: Any browser which supports Javascript, HTML5 and CSS3.

## Configuration
First of all, you need to confgure the database seeding, so:
1. Open the folder `seedMongo`;
2. Open the file `init.json` with an editor;
3. Just change the email field of the user present, this will be the Administrator email. The password is encrypted so you can not change it now, but using the procedure 'Forgot my password' when the system is up.
4. Now, open the file  `Backend\src\main\resources\application.properties` and than edit the email configuration settings the username, the port and the password of the email server. 
**N.B**.: In application.properties file you can set the database ip address, user and password if you are not interested in using the dockerized version.   

## Local installation

To install and run the application, simply follow this steps:

1. Open a terminal windows in `Mockup-V2/`
2. Run `docker-compose up` and wait until the services are loaded
3. Open the 'Backend' folder and run `mvn clean install exec:java`

At this point, the platform should be up and running at `localhost:8081`