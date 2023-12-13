# ChâTop

## Description
ChaTop API is a Spring Boot application that provides a RESTful API for managing chat and rental operations.

## Prerequisites

Before you begin, ensure you have met the following requirements:  
- You have installed the latest version of Java and Maven.
- You have a MySQL server running. If not, you can download it from here.
  
## Installation and launch
To install the application locally

1. Clone the repository:

```bash
git clone https://github.com/taylorfullstack/chatop_api.git
```

2. Navigate to the project directory:

```bash
cd chatop_api
```

3. Create a new database in your MySQL server:

```bash
mysql -u your_mysql_username -p -e "CREATE DATABASE IF NOT EXISTS chatop_db;"
```

Note: Replace your_mysql_username with your actual MySQL username.


4. Build the project and install its dependencies:

```bash
mvn clean install
```

5. Update the application.properties file with the correct database URL, username, password, and other configuration variables. You can use a .env file, or replace the variables in the application.properties file directly.

6. Launch the application

```bash
mvn spring-boot:run
```

Once the server is running, you can access the API endpoints at http://localhost:3001/api/.

## Documentation

After launching the application, the Swagger documentation can be found at http://localhost:3001/swagger-ui/index.html
