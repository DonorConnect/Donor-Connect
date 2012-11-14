Donors Connect
==============

This readme contains the instructions for setting up the development environment for the Donors Connect web site.

Contents
-------------
1. **What is Donors Connect?**
2. **Tech Stack**
3. **Repository Structure**
4. **Setup Instructions**

    i. Local Development Environment

    ii. CI Server/QA Environment/Showcase Environment

5. **Building the Project Locally**
6. **Software Installation**

    i. PhantomJS

    ii. MySQL


1. What is Donors Connect?
--

###Donors Connect is


**For** the Indian Middle Class

**Who** want to contribute to the welfare of society

**Donors Connect**
**Is a** website

**That** allows users to make an affordable contribution to a big cause

**Unlike** other options for donation in India

**Our Product** will allow users to keep track of their donations

***

**For** Charities in India

**Who** need funding for their projects

**DonorsConnect**

**Is a** website

**That** facilitates easy collection of funds for the Indian middle class

**Unlike** donorschoose & when we wish

**Our** Product is tailored to the Indian context


2. Tech Stack
--

* Web Framework - Spring MVC
* Database - [MySQL](http://www.mysql.com/)
* UI - [FreeMarker](http://freemarker.sourceforge.net/)
* Build Tool - [Gradle](http://www.gradle.org/)
* Functional Test - [Selenium](http://seleniumhq.org/)
* Unit Test - JUnit & Jasmine (using PhantomJS to run)
* Database Integration - [Hibernate](http://www.hibernate.org/)
* CI - [Go](http://www.thoughtworks-studios.com/go-agile-release-management/)
* Version Control - [GitHub](https://github.com/DonorConnect/)
* Payment - [ammado](https://www.ammado.com/)
* Web Server - Tomcat for server & Jetty for local debug
* Project Management Application - [Mingle](https://mingle01.thoughtworks.com/projects/donors_connect/) (requires an account to access)


3. Repository Structure
--

* Donor-Connect-App contains the source files required for the Donors-Connect web application
 - SqlFiles contains the database schema needed for creating database environment 
* Functional-Test contains the functional tests
* tools contains the required software for the CI server.


4. Setup Instructions 
--

###i. Local Development Environment

- Clone the project 

Use the following command to clone the project repository:

    git clone git@github.com:DonorConnect/Donor-Connect.git

- Install required software

  - PhantomJS
  - MySQL

You can find the installation instructions at the end of this document.


- IntelliJ setup

    - In order to initialize the IntelliJ project, run `./go cleanIdea ideaModule` in both `Donor-Connect-App` and `Functional-Test` directory.

    - And then open the Donor-Connect directory in IntelliJ, choose `File` -> `Add Module...`, and choose the .iml file in `Donor-Connect-App` and `Functional-Test` directory, respectively.

    - Then the IntelliJ project is ready for development.


**NOTICE:** The domain name `donorsconnect.com` is not a registered domain as yet but the Payment API (Ammado) will redirect the page to `donorsconnect.com` after donation. 

- Modify the hosts file
 - Open  `/etc/hosts` in an editor and add the following line to it: `127.0.0.1  www.donorsconnect.com`

    


###ii. CI Server/QA Environment/Showcase Environment

- Clone the repository at `git@github.com:DonorConnect/Infrastructure.git`
and follow the instruction of installing go-server and go-agent in the readme file.


5. Building the Project Locally
--

In order to run the build switch folders to `Donor-Connect-App` and run `./go build` in cmdline.

The build will compile and create a war package with its dependencies in the build folder called `Donor-Connect-App.war`, in the `Donor-Connect-App/build/libs/` directory.

6. Software Tools Installation
--

###i. PhantomJS

For Linux machines, the build will automatically use the version in the `Donor-Connect-App/tools` directory. For other operating systems you will need to install the appropriate version on your machine from [http://phantomjs.org/download.html](http://phantomjs.org/download.html).

You will need to setup the `CLASSPATH` variable to phantomjs with the directory of your installation.

Add the following lines to your `.bash_profile` file in your user home directory `~/.bash_profile`.

    PHANTOMJS_HOME=[your installation path here]/phantomjs-1.7.0-macosx
    export PHANTOMJS_HOME
    export PATH=$PATH:$PHANTOMJS_HOME/bin

Run your profile file using `. .bash_profile` and type `phantomjs` in your terminal to make sure that the path is set properly.

###ii. MySQL
Go to [MySQL Download Page](http://dev.mysql.com/downloads/mysql/) to download version 5.5.28 of MySQL Community Server, and install.
