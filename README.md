Donor-Connect

Donor-Connect-App is used for the web application

Functional-Test is used for the functional tests

Intellij Setup
==============

In order to initialize the intellij project, run "./go cleanIdea ideaModule" in both Donor-Connect-App and Functional-Test directory.
And then open the Donor-Connect directory in intellij, choose File -> Add Module..., and choose the .iml file in Donor-Connect-App and Functional-Test directory, respectively.
Then the intellij project is ready for development.

Development
===========

In order to run the build switch folders to Donor-Connect-App and run './go build' in cmdline.
The build will compile and create a war package with its dependencies in the build folder called Donor-Connect-App.war, in the Donor-Connect-App/build/libs/ directory.

PhantomJS
=========

For Linux machines, the build will automatically use the version in the Donor-Connect-App/tools directory. For other operating systems you will need to install the appropriate version on your machine from http://phantomjs.org/download.html.
You will need to setup the CLASSPATH variable to phantomjs with the directory of your installation.