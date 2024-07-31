# Java-Playwright-Framework

### Prerequisites
1. Install and set environment variable for java.
    * Windows - https://www.oracle.com/java/technologies/downloads/
    * Linux - ```  sudo apt-get install openjdk-8-jre  ```
    * MacOS - Java should already be present on Mac OS X by default.
2. Install and set environment varibale for Maven.
    * Windows - https://maven.apache.org/install.html
    * Linux/ MacOS -  [Homebrew](http://brew.sh/) (Easier)
    ```
     install maven
    ```
3. Install Playwright

### Run your First Test
1. Clone the Data-Drivern-Framework repository. 
```
git clone https://github.com/HardikDuva/JavaPlaywrightFramework.git and make sure all the dependency in pom.xml file are upto date
```
2. Set-up Test Data file and configuration file
Make sure all the necessary data are there.

3. Write test case for Login Page(You can write test case for any page)
4. Set-up Test-Data and configuration file.

5. Create page object from pages and declare webelement in FindBy 
Generally playwright doesn't support FindBy but here we have created method in AbstarctionPOM file and also declare
selector in FindBy interface

6. Create TCS.

7. Create testsuitefile according to requirnment and run it with required parameter.
