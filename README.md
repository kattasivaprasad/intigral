# intigral

This repository contains the tests for Intigral Promotions Rest Service with Cucumber and Junit4 and Allure Reporting

# CodeGuidelines
Code should be clean and kept to the same standard as production code - make sure unused variables and methods are removed, code is correctly formatted, with no random newlines and whitespaces.

# Installations

You should have Java installed in your system and must set environment variables for JAVA_HOME
Allure cli tool has to be installed in your system to see the report in local

# How To Run the Tests

1. Clone the Project
2. Import the project to your IDE
3. Go to terminal on your IDE
4. Run "mvn clean install" command and make sure the build is successful
5. Open TestRunner.java file
6. Right click on the file
7. Select Run TestRunner

Note:
This will run the whole feature file. If need the execution of particular scenario then
change the tag name accordingly.

#How to check the report:
Once the execution completed
1. Open terminal and navigate to your project location
2. Once you are in project location go to target folder using command (cd target)
3. Enter the command allure serve
4. The report will be generated and will be automatically opened in your browser


Note:
You should have allure cli installed in your system to see the report in browser. The below command will install the allure cli

"npm install -g allure-commandline --save-dev"


Please delete the allure-results folder under target frequently.


