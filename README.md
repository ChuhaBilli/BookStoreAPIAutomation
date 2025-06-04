# SpurQ-Labs-Book Store Automation (Java-API-Cucumber-RestAssured)

## **Overview:**
This is an test automation project for testing of following endpoints for a book store

healthCheckEndPoint=/health/ 
loginEndPoint=/login
registrationEndPoint=/signup
booksEndPoint=/books/
bookByIdEndPoint=/books/{id}

where user can add a book, update, fet the specific book via id or get all the books in db.

For using the APIs user needs to 1st register and then login, once logged in user can authenticate using the brearer token which has been emulated in the test automation project.

## **About framework:**
This is an initial commit with basic features sufficient to test the Sanity.
Currently Feature file has examples of sending the dynamic request using pojos and validate response json body.
Future scope of work planned has more feature like:
1. Support for file based static test data.
2. Detailed regression
3. Tests scripts with error scenarios.
4. CI/CD plugins.
5. Auto email support.
6. More format of reports supported.

## **Required Setup:**

- [Java](https://www.guru99.com/install-java.html) should be installed and configured.
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) should be installed and configured.
- [GIT](https://phoenixnap.com/kb/how-to-install-git-windows).
- Download the files from Git repository either as zip file OR use [Git] to clone

## **Steps to execute:**

1. Go to project root: <yourPath>\BookStoreAPIAutomation
2. Run following command: mvn clean test -Dcucumber.options="src/test/resources/features/BookStore/<featureFileYouWantToRun>.feature --tags @<yourTag>"
                 example: mvn clean test -Dcucumber.options="src/test/resources/features/BookStore/BookOperations.feature --tags @sanity"

## **Report: **
1. Cucumber Report: /target/cucumber-reports/cucumber-pretty.html
