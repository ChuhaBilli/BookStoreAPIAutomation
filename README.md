# SpurQ-Labs-Book Store Automation (Java-API-Cucumber-RestAssured)

## **Overview:**
This is an test automation project for testing of following endpoints for a book store

	/health : for checking if the services is up and running.
	/signup: for signing up a user.
	/login: for logging in a signed up user.
	/books: for getting the list of all the books added, adding new books.
	/books/{id}: for updating or gettign a specific book entry.

where user can add a book, update, fet the specific book via id or get all the books in db.

For using the APIs user needs to 1st register and then login, once logged in user can authenticate using the brearer token which has been emulated in the test automation project.

## **About framework/Implementation:**
Framework is developed using Java language, cucumber tool is added to enable gherkins based test scripts. For Interacting with endpoints rest assured is used. 
Tests for all major functionalities with assertions added at various steps for verification of the book store APIs implemented. 
Framework is integrated with JUnit runner and designed in a scalable with a re-usable REST interface layer.

## **Current Status and Furture Scope:**
1. Currently Feature file has examples of sending the dynamic request using pojos and validate response json body.
2. Both +ve and -ve scenarios has been added to test the APIs.
3. Validations are added for status codes, data in response payload, headers and errors.
4. Chaining has been done where the response of containing the token is further used of authentication.
5. Tags like sanity, regression, error are using in the test cases to provide for flexible execution using cucumber.
6. CI-CD config file provisioned, which needs to be updated based on the actual environment available.
7. Different configuration file for different environment was provided, seperate files were provided as the project develops the list of variables increases and becomes complex to be mainted in a single file.

Future scope of work planned (which were not complete due to time constriant.)

1. Auto email support with report.
2. More format of reports supported, currently only cucumber report was provided due to time constraint.
3. TestNG runner along with JUnit runner.
4. More static test data driven scenarios for regression, which were constrianed as of now due to time availability.
5. Json Schema validation for response.


## **Prerequisites (Setup) for Test Execution:**

Install the following (for installation instructions refer the hyperlinks below)

- [Java](https://www.guru99.com/install-java.html): [OpenJDK 17+].
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/): [version 3.9.9]
- [GIT](https://phoenixnap.com/kb/how-to-install-git-windows)
- IDE of preference (optional). This project was deloped using [eclipse](https://www.eclipse.org/downloads/packages/installer) Version: 2025-03 (4.35.0)


## **Steps To Execute Test:**

1.a Clone the repo via git using command 
	```bash
	git clone https://github.com/ChuhaBilli/BookStoreAPIAutomation.git
	```

OR 

1.b Download the source code as zip from [here](https://github.com/ChuhaBilli/BookStoreAPIAutomation) and then unzip to desired directory on your machine.

2. Go to project root: 
	```bash
	yourPath/BookStoreAPIAutomation
	```
3. Run following command: 
	```bash
	mvn clean test -Dcucumber.options="src/test/resources/features/BookStore/BookOperations.feature --tags @sanity"
	```

note: Command above runs a specific feature file, kindly update the feature you want to run. 
In the tests listedin the feature file mentioned abve 1 scenario fails due to a potential bug.

## **Report:**
Cucumber Report: report can be found at followign location:
	 ```bash
	/target/cucumber-reports/cucumber-pretty.html
	```
 open the .html file in the preferred browser.

## **Test Strategy:**
1. First of all the scope of the testing was explored by doing the functional testing.
2. All the endpoints were tested manully using Postman tool for all CURD operations.
3. While testing, both positive and negative scenarios were explored and listed down with observations.
4. Performance testing was skipped and kept out of scope as of now.
5. Data consistency and integrety was also tested.
6. Test Scenarios were written in gherkins.
7. Automation framework was created and gherkins tests added.
8. Test sets were created for each endpoint seperately.
9. Test flow for each endpoint create using a new user and then executes a flow for that user after login at runtime.
10. For developing the flow of the test cases it was kept in mind that test can be executed on any machine which not necessirialy
have the pre loaded test data and hence new test data is created each time. which makes the test execution reliable.
11. Provision was planned to load the pre defined test data as a mandatory 1st step and then execute some other specific set of regression, but it could not be completed due to time limitations.
12. Also wanted to add the provision for cleaning up the data base before execution which is also not added as of now due to limited time for assignment.

**Challanges Faced:**
1. Junit runner execution was not giving any logs for failure to execute on local machine which made it hard to debug.
2. Adding allure reports needs some work in adding the hooks which was a spill over for given time frame.
2. Chaining of cucumber feature files is not yet possible due to which each feature file needs to be able to run and execute independently which makes it challanging to develop 
the test that work on predefined loaded data.
4. Dont have the details on the database table implementation and interface which made it difficult for creating test cases with predefined data.
5. For running test whithout breaking for each flow a new user has to be created everytime to avoid duplicated test data or user 
for that a prefix was added to each user name and email with timestamp upto miliseconds. if these need to be run in parallel then thread id also needs to be appended to make the names unique in runtime.




