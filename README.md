# GUI Test Automation using Selenium

## Important feature
1. Used bonigarcia library no need to pass driver path it will read by itself
2. Put the driver.exe in the root directory it will load automatically.
3. Bonigarcia library using git hub dependency refer ``

## Features supported
1. Dependency management and project management by using MAVEN. 
2. Functional UI Automation by using Selenium.
3. TestNG for testing workflow.
4. GIT as distributed version-control system.
5. Modular Approach via Page Object model.
6. Browser supported - Chrome, IE and Firefox.
7. HTML Report by including Extent Reports.
8. Logging via Log4j.
9. Property Reader to read Test data from properties files.
10. Implements page Factory to initialize pages.
11. Follows clean architecture(only calling in test runner classes).
12. Archived Last execution results by utilizing `java.util.zip`. 
13. Custom assertion to print custom message when assertion fails.
14. Used soft and hard Assertion.
15. Capture screen shots for failed testcases.
16. Implicit and Explicit waits are considered for Windows to Load and Webelement to load.
17. Used data providers from excel to read data.
18. Add listners to add result for skipped test cases in extent report and in logs.


### Pre-requisites: ###
* Download and install Java Development Kit (JDK 1.8)
* Download and install Intellij IDEA IDE (recommended)
* Download and install Maven
* Chrome driver 

## To view Report 
1. Go to the root directory under `SeleniumProject/CurrentReports/Reports/Report.html`
2. All past reports are saved under `SeleniumProject/ArhivedReports/<yyyy_mm_dd_hh:mm:ss>.zip/Report.html` 

### Project Setup: ###
* Set the path of chrome driver in TestDataSheet prersent in Root Directory 
`ChromeDriverPath = Your chrome driver path`
* Open/Import project in Intellij/eclipse IDE
* Reimport pom.xml and build project once

### Run tests
* Run by right click testng.xml file and run with coverage
* Run by maven use test command

## Git Repository for the source.
`https://github.com/arjitkathuria/Selenium.git`