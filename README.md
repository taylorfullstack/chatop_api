# ChâTop

## Description
ChâTop API is a Spring Boot application that provides a RESTful API for managing chat and rental operations.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- You have installed the latest version of Java and Maven.
- You have a MySQL server running. If not, you can [download it from here](https://dev.mysql.com/downloads/installer/).

## Local installation and launch

### 1. Clone the repository:

```bash
git clone https://github.com/taylorfullstack/chatop_api.git
```

### 2. Navigate to the project directory:

```bash
cd chatop_api
```

### 3. Create a new database in your MySQL server:

   Open a terminal and run the command

  ```bash
  mysql -u mysql_username -p < chatop.sql
  ```

Important Note: Replace `mysql_username` with your actual MySQL username.

### 4. Update the application.properties file with the correct server, database, and security variables:

  You may use a .env file (recommended), or replace the variables in the application.properties file directly.
  
  The following variables need to updated with your own values
  
  ```properties
  server.port=${SERVER_PORT} #example: 3001
  server.url=${SERVER_URL} #example: http://localhost:3001
  
  #use the database URL, username, and password from your MySQL server
  spring.datasource.url=${DB_URL}
  spring.datasource.username=${DB_USERNAME}
  spring.datasource.password=${DB_PASSWORD}
  
  jwt.key=${JWT_KEY} #a secure, random string (UUID recommended)
  client.url=${CLIENT_URL} #example: http://localhost:4200
  springdoc.swagger-ui.oauth.clientSecret=${JWT_KEY} #the same secure, random string (UUID recommended) used for jwt.key
  ```

### 5. Build the project and install its dependencies:

```bash
mvn clean install
```

### 6. Launch the application

```bash
mvn spring-boot:run
```

Once the server is running locally, you can access the API endpoints at http://localhost:3001/api/.

Note: Be sure to replace `3001` with your server port in the URL.

## Swagger Documentation

After launching the application locally, the Swagger documentation can be found at http://localhost:3001/swagger-ui/index.html

Note: Be sure to replace `3001` with your server port in the URL.

From here, you can access all routes that do not require authentication.

To access authenticated routes:
- Either login with the test user or register a new user and login. 
- Copy the returned jwt token, click the authorize button, paste the token into the form in the modal and click authorize. 
- You will now be able to access authenticated routes as this user until you click the authorize button again and then click logout.

Note: The test user's email and password are located in the [chatop.sql script](https://github.com/taylorfullstack/chatop_api/blob/master/chatop.sql) as well as in the Postman collection.

## Try it out!

To try out the application routes, you may use the Swagger UI as described above, the front end Angular application, Postman, or all three!

- Install the corresponding [front end Angular application here](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring) and login with the test user.
- Import the [Postman Collection](https://github.com/taylorfullstack/chatop_api/blob/master/ChaTop.postman_collection.json) into Postman. Follow the instructions in the description to set up your jwt token variable.
