#!/bin/bash

if [ "$1" == "linux" ]
then
    ../tools/phantomjs-1.7.0-linux-x86_64/bin/phantomjs src/test/javascript/lib/run_jasmine_test.coffee src/test/javascript/TestRunner.html
elif [ "$1" == "mac" ]
then
    phantomjs src/test/javascript/lib/run_jasmine_test.coffee src/test/javascript/TestRunner.html
else
	echo "Cannot find phantomjs installation. If you have a Mac please install the version 1.7.0"
fi
