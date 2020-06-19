# # Rest Assured Tests


Purpose:
----------
Automated tests for https://stackexchange.com/

Description:
------------
Test cases are automated using Selenium + Java and used TestNG Runner.

Created Maven Project and added dependencies for selenium,Rest-assured,TestNG. 

Test Folder contains the API.java file which contains different APIs from the website. 

Apis are referred from http://api.stackexchange.com/docs




Executing:
-------------
The test is ready to use for CI integration. Just run the below command for the clean tests.

Run the Tests in the Terminal:

Execute the below command – mvn clean test

Environment Configuration - While working with APIs, we often need different setups for the local machine or server, the development server, or the production API server. Environments let us customize requests using placeholders or variables so that the API signatures can easily switch between different setups without changing the requests.


Currently I am running them on my local machine. So it should work same in your machine too.


Report - You can find the reports in the folder Target folder/surefire-report. Click on index.html and open it in the browser of your choice.

