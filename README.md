Inventory Management System

Built using- Spring Boot (Backend Rest API) Mysql (Database)

Tech Stack- Java -21 Spring Boot -4.1.1 MySQL - 8.0

Project Structure- ems-> REST api-> spring data jpa -> mysql

Dependencies - actuator, data-jpa, security, thymleaf, validation, webmvc, springsecurity6, test, lombok, mysql-connector-j

How to run the project- 
step 1 - Clone the repository https://github.com/Nidhigit01/SpringbootBasic.git 
step 2 - Run the DemoApplication.java 
step 3 - Go to Postman and test following urls: 
method - URL 
POST http://localhost:8080/api/products
GET http://localhost:8080/api/products/1{id} 
GET http://localhost:8080/api/products?page=<pageno.>&size=&sortBy= 
PUT http://localhost:8080/api/products/{id} 
DELETE http://localhost:8080/api/products/{id}
step 4 - Go to browser and test-
http://localhost:8080/- dashboard, Inventory Management System, View Products
http://localhost:8080/products - create products
http://localhost:8080/products/new - add products
http://localhost:8080/products/save - save products
http://localhost:8080/products/edit/{id} - update products
http://localhost:8080/products/delete/{id} - delete products
