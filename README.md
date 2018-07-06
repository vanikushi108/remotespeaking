
# Remote Speaking Functional Tests

Bit Bucket path : https://bitbucket.org/carepo/remote-speaking-functional-tests


Key Features
============
tests running on Samsung Galaxy Tab


System Requirements
===================
Java version: 1.8.0_65
Apache Maven 3.3.9


To run the tests use below commands
========================

Sauce Labs - Samsung Galaxy Tab
===================

mvn clean mvn clean -Dbrowser=Chrome -Drun_type=android -Ddevice='Samsung Galaxy Tab S3 GoogleAPI Emulator' -Dcucumber.options="--tags @regression --format html:target/cucumber" -Denvironment.name=stable test

Sauce Labs - Chrome
===================

mvn clean mvn clean -Dbrowser=Chrome -Dbrowser_version=67.0 -Dos_version='Windows 10' -Drun_type=saucelabs -Dcucumber.options="--tags @regression --format html:target/cucumber" -Denvironment.name=stable test

Local - Chrome
===================

mvn clean -Dbrowser=Chrome -Dbrowser_version=67.0 -Dos_version='Windows 10' -Drun_type=local -Dcucumber.options="--tags @regression --format html:target/cucumber" -Denvironment.name=stable test

Test reports path
========================
target/cucumber/index.html

### Environments ####

  #stable.properties file to run on stable environment#
