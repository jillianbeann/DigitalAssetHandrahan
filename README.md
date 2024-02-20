# Portfolio Management CLI

## Overview
This CLI program allows users to manage portfolios and their associated share classes through a command-line interface. Users can perform various operations such as counting, listing portfolios, and showing share classes, and interact with the data stored in Portfolio.CSV and PortfolioShareClass.CSV.

## Features
- Automatic loading and population of portfolio and share class data from CSV files.
- Support for four commands: help, COUNTS, LIST PORTFOLIOS, and SHOW SHARE CLASS.
- Displays total number of portfolios and share classes loaded from CSV files.
- Lists all portfolios with their share class counts.
- Shows share classes for a specific portfolio based on its code.
- Error handling for file loading failures.

## How to Run
1. Clone the repository to your local machine.
2. Ensure you have Java installed on your machine.
3. Ensure you have Maven installed on your machine.
4. Open a terminal or command prompt.
5. Navigate to the project directory.
6. Compile the Java files: `javac *.java`
7. Run Maven to resolve dependencies: `mvn compile`
8. Run the CLI program: `java CLIMain`

## Usage
- Upon running the program, it automatically loads portfolio and share class data from Portfolio.csv and PortfolioShareClass.csv files.
- After successful loading, the program prompts the user to enter a command.
- Enter one of the following commands:
  - `COUNTS`: Display total number of portfolios and share classes loaded.
  - `LIST PORTFOLIOS`: Display a list of all portfolios with their share class counts.
  - `SHOW SHARE CLASS`: Display share classes for a specific portfolio. Must provide the code for the desired portfolio.
- Follow the on-screen instructions to execute commands and interact with the program.

## File Structure
``` bash 
src
├── com
│   ├── cli
│   │   ├── CLI.java
│   │   └── CLIMain.java
│   ├── model
│   │   ├── Portfolio.java
│   │   └── PortfolioShareClass.java
│   └── parser
│       └── CSVParser.java
└── module-info.java
pom.xml
Portfolio.csv
PortfolioShareClass.csv
```
## Dependencies
- Java 8 or later
- Maven
- openCSV 5.5.1
