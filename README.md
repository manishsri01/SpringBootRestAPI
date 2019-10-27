#About the Technology and frameworks
Project is using:
-Open JDK 11
-Spring Boot framework
-Maven build system
-MySQL database
-Swagger UI and doc
-Spring Security(Basic-AUTH)

#How to import & setup project?
-Clone SpringBootRestAPI project locally
-Import Project as Maven project
-Go to SpringBootRestAPI>sql>db_test.sql location and create new MySQL database with the help of given script
-Now try to run project as maven project using command-”spring-boot: run"
-Go to browser and type "http://localhost:8080/swagger-ui.html" 
Note: application require basic0-authorigation please use any user from database for example table-user contains username-"user1" & password-"test123". Please use this user for authentication.

#How to use Swagger UI and doc?
-As soon you will authorize the application will see swagger-page where user can test all api's and can check api's doc using link-http://localhost:8080/v2/api-docs on the page.

-Application contains user & message controller
-message-controller
-user-controller
Note: This step is totally optional, user may use any rest client of their choice it just helpful to know about api's and attributes.

#Testing:
-Project is using "Spring Boot Integration Testing" for web layer
-In order to run the test simply user can run individual test-class as Junit or
-Use Run As>Maven Test
