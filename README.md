# JunitSample
----------------------------------------------------------------------------
Pre-requisit to run this project.
1.Java 7 or higher
2. Maven 3.05 or higher
3. create database using anydb (in this example mysql8)
   1.create database junitsampledb;
   2.create table USER(USER_ID varchar(20) not NULL primary key,PASSWORD varchar(20));
4.Update jdbc.properties file with database details   
-----------------------------------------------------------------------------
How to run the test cases:
Just go to project root folder and run following command
run command : mvn clean test
-----------------------------------------------------------------------------
How to genrate reports:
run command: mvn site
You may use stand alone goal: mvn surefire-report:report
------------------------------------------------------------------------------
How to open test report:
Go to JunitSample\target\site\index.html

Open this file in web browser and go to project reports>click Surefire Report

This will show you summary of all test cases in details
-----------------------------------------------------------------------------
How to skip test cases if need
run command mvn install -DskipTests
======*======================*=======================*=========================
