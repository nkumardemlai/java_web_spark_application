# Java Web Spark Application

A simple Java web application built with the [Spark Framework](http://sparkjava.com/).

## Overview

This is a lightweight web application demonstrating the Spark Java framework's capabilities. It provides several HTTP endpoints including HTML and JSON responses.

## Features

- RESTful API endpoints
- JSON response handling
- HTML page rendering
- CORS support
- Error handling (404, 500)
- Configurable port via environment variable

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
java_web_spark_application/
├── pom.xml                     # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/sparkapp/
│   │   │       └── App.java    # Main application class
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/example/sparkapp/
│               └── AppTest.java # Unit tests
└── README.md
```

## Building the Application

To compile and package the application:

```bash
mvn clean package
```

This will create an executable JAR file in the `target/` directory.

## Running the Application

### Using Maven

```bash
mvn exec:java -Dexec.mainClass="com.example.sparkapp.App"
```

### Using the JAR file

```bash
java -jar target/spark-web-app-1.0.0.jar
```

### Configuring the Port

By default, the application runs on port 4567. You can change this by setting the `PORT` environment variable:

```bash
PORT=8080 java -jar target/spark-web-app-1.0.0.jar
```

## Testing the Application

Run the unit tests:

```bash
mvn test
```

## Available Endpoints

Once the server is running, you can access the following endpoints:

### Web Pages

- **GET /** - Home page with links to all endpoints
  ```
  http://localhost:4567/
  ```

### API Endpoints

- **GET /hello** - Simple hello message
  ```
  http://localhost:4567/hello
  ```

- **GET /hello/:name** - Personalized greeting
  ```
  http://localhost:4567/hello/World
  ```

- **GET /api/info** - Application information (JSON)
  ```
  http://localhost:4567/api/info
  ```
  Response:
  ```json
  {
    "application": "Spark Web Application",
    "version": "1.0.0",
    "framework": "Spark Java",
    "description": "A simple Java web application"
  }
  ```

- **GET /api/status** - Server status (JSON)
  ```
  http://localhost:4567/api/status
  ```
  Response:
  ```json
  {
    "status": "running",
    "timestamp": 1234567890,
    "uptime": 42
  }
  ```

## Testing with curl

```bash
# Test the home page
curl http://localhost:4567/

# Test the hello endpoint
curl http://localhost:4567/hello

# Test personalized greeting
curl http://localhost:4567/hello/YourName

# Test API info endpoint
curl http://localhost:4567/api/info

# Test API status endpoint
curl http://localhost:4567/api/status
```

## Dependencies

- **Spark Java** (2.9.4) - Micro web framework
- **Gson** (2.10.1) - JSON processing
- **SLF4J** (2.0.9) - Logging facade
- **JUnit Jupiter** (5.10.1) - Testing framework

## License

This project is open source and available for educational purposes.