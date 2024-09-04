# console calculator

## Project Overview
The Console Calculator is a simple command-line application built in java that performs basic arithmetic operations such as addition, subtraction, multiplication, and division.
The project is designed to serve as an introduction to java programming, object-oriented principles, and test-driven development (TDD). It also aims to provide hands-on experience with setting up continuous integration (CI) pipelines with Github Actions.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
- [Development](#development)
  -  [Project Structure](#project-structure)
  -  [Testing](#testing)
  -  [CI/CD Pipeline](#cicd-pipeline)
- [Roadmap](#roadmap)
- [Contributing](#contributing)

## Features
- Basic Arithmetic Operations: Perform addition, subtraction, multiplication, and division.
- Error Handling: Gracefully handle errors such as division by zero and invalid input
- Interactive Command-Line interface: Simple and intuitive CLI for user interaction.
- Test-Driven Development: Fully tested codebase with unit tests for each operation
- Continuous Integration: Automated build and testing using GitHub Actions.

## Getting Started
### Prerequisites
Before you begin, ensure you have the following installed on your machine:
- Java Development Kit (JDK) 17 or later
- Maven(for project management and build)
- Git(for version control)

### Installation
1. Clone the Repository
```bash
git clone https://github.com/lurldgbodex/console-calculator.git
cd console-calculator
```
2. Build the Project
Use maven to build the project and download dependencies
```bash 
mvn clean install
```
### Usage
1. Run the Application
After building, you can run the application from the command line:
 ```bash
 java -jar target/console-calculator-1.0-SNAPSHOT.jar
 ```
2. Using the Calculator
Follow the on-screen prompts to perform arithmetic operations. Example:
```bash
Welcome to Console ArithmeticOperation!
Please enter the first number: 10
Please enter the operator (+, -, *, /): +
Please enter the second number: 5
Result: 10 + 5 = 15
```

## Development
### Project Structure
The project follows a standard maven structure:
```agsl
|-- src/
|   |-- main/
|   |   |__java/
|   |
|   |-- test/
|   |    |__java/
|   |   
|-- .github/
|   |__workflows/
|   |    |__ci.yml
|   |   
|-- pom.xml
|__ README.md
```

### Testing
This project is developed using Test-Driven Development (TDD). Unit tests are written for each arithmetic operation.
1. Running Tests
   Use Maven to run the tests:
    ```bash
    mvn test
    ```
2. Test Coverage
    Ensure all operations are thoroughly tested, including edge cases like division by zero.

### CI/CD Pipeline
This project uses GitHub Actions for Continuous Integration(CI). The pipeline is defined in `.github/workflows/ci.yml` and includes the following steps:
- Build: Compile the Java code using maven.
- Test: Run the unit tests to ensure code quality
- Static Code Analysis: Use tools like CheckStyle to enforce coding standards.

You can view the CI pipeline status by checking the Actions tab on the GitHub repository.

## RoadMap
- Version 1.1:
  - Implement support for more complex operations(e.g, exponentiation, modulus)
  - Add support for floating-point arithmetic
- Version 1.2:
  - Implement history functionality to allow users to review past calculations.
  - Add a command-line option for batch processing of multiple operations.

## Contributing
Contributions are welcome! If you have suggestions for improvements or new features, feel free to fork the repository and create a pull request.
1. Fork the Project
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. commit your changes(`git commit -m'add some amazing feature`)
4. Push to the branch(`git push origin feature/amazing-feature`)
5. open a pull request