### About the Technology and frameworks
Project is using:  
-Open JDK 11 OR 12  
-Spring Boot framework  
-Maven build system  
-MySQL database  
-Swagger UI and doc  
-Spring Security(Basic-AUTH)  

### How to run project without any setup?  
-Please copy 'runable' folder from the root folder and paste anywhere locally or just go to the same place
-Go to SpringBootRestAPI>sql>db_test.sql location and create new MySQL database with the help of given script  
-and change 'application.properties' as per your databse credentials
![test5](https://user-images.githubusercontent.com/4569362/67636977-4f61a580-f8d6-11e9-8821-10e31734db07.PNG)
-Now you can run this jar using command: "java -jar Backend.jar"
-If everything went right, you can open browser and start testing("http://localhost:8080/swagger-ui.html")

### How to import & setup project?  
-Clone SpringBootRestAPI project locally  
-Import Project as Maven project  
-Go to SpringBootRestAPI>sql>db_test.sql location and create new MySQL database with the help of given script  
-Please change the databse credentials from "application.properties"

![test5](https://user-images.githubusercontent.com/4569362/67636977-4f61a580-f8d6-11e9-8821-10e31734db07.PNG)

-Now try to run project as maven project using command-”spring-boot: run"  
-Go to browser and type "http://localhost:8080/swagger-ui.html"   
Note: application require basic-authorigation please use any user from database for example table-user contains username-"user1" & password-"test123". Please use this user for authentication.  

### How to use Swagger UI and doc?  
-As soon you will authorize the application will see swagger-page where user can test all api's 

![test1](https://user-images.githubusercontent.com/4569362/67636973-41ac2000-f8d6-11e9-8b35-ff9a431c5a3d.PNG)

-And can check api's doc using link-http://localhost:8080/v2/api-docs on the page.   

![test2](https://user-images.githubusercontent.com/4569362/67636974-4670d400-f8d6-11e9-94cf-17b707495641.PNG)

-Also user can convert this json doc into yaml using Swagger-Editor: https://editor.swagger.io/  

![test3](https://user-images.githubusercontent.com/4569362/67636976-496bc480-f8d6-11e9-82e1-3354fb16da58.PNG)

-Application contains user & message controller  
-message-controller   
-user-controller  
Note: This step is totally optional, user may use any rest client of their choice it just helpful to know about api's and attributes.  

![test6](https://user-images.githubusercontent.com/4569362/67636983-699b8380-f8d6-11e9-89d7-84150e5a5123.PNG)

### Testing:  
-Project is using "Spring Boot Integration Testing" for web layer  
-In order to run the test simply user can run individual test-class as Junit or  
-Use Run As>Maven Test  
