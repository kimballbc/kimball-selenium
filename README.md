# Selenium Test Project

This project contains automated browser tests using Selenium WebDriver and Java.

## Setup

This project uses Maven for dependency management. The main dependencies are:

- Selenium WebDriver (4.17.0) for browser automation
- WebDriverManager (5.6.3) for automatic driver management
- JUnit 5 (5.10.2) for test assertions and running

## Project Structure

```
src/
├── main/
│   └── java/        # Main application code (if needed)
└── test/
    └── java/        # Test classes 
```

## Running Tests

To run tests using Maven:

```bash
mvn clean test
```

## Prerequisites

- Java 11 or higher
- Maven
- Web browsers (Chrome, Firefox, etc.) 