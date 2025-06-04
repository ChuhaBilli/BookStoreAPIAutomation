package stepsDef;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.AddBookContract;
import pojo.SignupContract;
import pojo.UserLoginContract;

import org.junit.Assert;

import TestsContext.Config;
import TestsContext.TestContext;
import utils.CommonUtils;
import utils.API.DynamicJsonGenerator;
import utils.API.RESTAPI;
import utils.API.RESTRequestHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StepsDef_BookStore {
	
	TestContext testContext = TestContext.getInstance();
	Config config = testContext.getConfig();
	RESTAPI api = new RESTAPI(); //TODO: remove the dependency of this call and fetch all directly here
	RESTRequestHandler reqHandler = new RESTRequestHandler();

	
    //TODO: methods from book store class and RESTAPI class to be called here and get rid f those classes

    //Common
    @Then("^Validate Response Status code \"([^\"]*)\"$")
    public void validateResponseStatusCode(int expectedStatusCode) {
        //int actualStatusCode = bsBasic.getResponse().getStatusCode();
    	int actualStatusCode = testContext.getResponse().getStatusCode();
        Assert.assertEquals("FAILED: Unable to verify expected status code -"+expectedStatusCode+", Actual Status code -"+actualStatusCode, expectedStatusCode, actualStatusCode);
    }

    @Given("^Set user details$")
    public void createSetUserDetails() {
    	
    	
        String randomEmail = CommonUtils.randomString(6, true, false) + "@" + CommonUtils.randomString(5, true, false) + ".com";
        String password = "123456";
        
        //Registration Request
        SignupContract signupContract = SignupContract.builder()
                .email(randomEmail).password(password).build();
        String url = config.getUri()+config.getRegistrationEndPoint();
        Response response = reqHandler.sendPostRequest(signupContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL,url , Collections.emptyList(), Collections.emptyMap());
        
        //Login Request
        UserLoginContract userLoginContract = UserLoginContract.builder()
                .email(randomEmail).password(password).build();
        url = config.getUri()+config.getLoginEndPoint();
        
        response = reqHandler.sendPostRequest(userLoginContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url, Collections.emptyList(), Collections.emptyMap());
        testContext.setResponse(response);

    }

    //Health check
    @When("^Hit Request for Health Check$")
    public void hitRequestForHealthCheck() {
        
        String url = config.getUri()+config.getHealthCheckEndPoint();
        Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
        testContext.setResponse(response);
    }

    @And("^Verify status response message for health check$")
    public void verifyStatusResponseMessageForHealthCheck() {
    	
        Assert.assertEquals("FAILED: Unable to verify status response for health check", 
        		"up", testContext.getResponse().jsonPath().get("status"));
    }

    //Register user
    @When("^Hit Request for Register User \"([^\"]*)\" and \"([^\"]*)\"$")
    public void hitRequestForRegister(String email, String psw) {
    	
    	
    	String url = config.getUri()+config.getRegistrationEndPoint();
        SignupContract signupContract = SignupContract.builder()
                .email(email).password(psw).build();
        Response response = reqHandler.sendPostRequest(signupContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url, Collections.emptyList(), Collections.emptyMap());
        testContext.setResponse(response);
    }

    @And("^Verify success response message for registered user$")
    public void verifySuccessResponseMessageForRegisteredUser() {
        Assert.assertEquals("FAILED: Unable to verify success response for Register User", 
        		"User created successfully", testContext.getResponse().jsonPath().get("message"));
    }

    //Login User
    @When("^Hit Request for Login User \"([^\"]*)\" and \"([^\"]*)\"$")
    public void hitRequestForLogin(String email, String psw) {

        UserLoginContract userLoginContract = UserLoginContract.builder()
                .email(email).password(psw).build();
        String url = config.getUri()+config.getLoginEndPoint();        
        Response response = reqHandler.sendPostRequest(userLoginContract, 
        		DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url, Collections.emptyList(), 
        		Collections.emptyMap());
        testContext.setResponse(response);
        
    }

    //Add Book
    @When("^Hit Request for Add Book Name \"([^\"]*)?\", Author \"([^\"]*)?\", Published Year \"([^\"]*)?\", Book Summary \"([^\"]*)?\" with new user$")
    public void hitRequestForAddBook(String bookName, String author, String publishedYear, String bookSummary) {
        
    	Map<String, String> headersKeyValue = new HashMap<>();
        headersKeyValue.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0MS5jb20iLCJleHAiOjE3NDg1MjYwNTZ9.NA6c8yDAo2cqC9diF77L7enTgY81H7etAPtQkAg6oS4");
        headersKeyValue.put("Connection", "keep-alive");
        
        AddBookContract addBookContract = AddBookContract.builder()
                .name(bookName).author(author).published_year(publishedYear).book_summary(bookSummary)
                .build();
        String url = config.getUri()+config.getBooksEndPoint();
        
        //TODO review this. instead create update headers method in Request Handler Class.
        this.reqHandler = new  RESTRequestHandler(headersKeyValue, ContentType.JSON);
        Response response = reqHandler.sendPostRequest(addBookContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url, Collections.emptyList(), Collections.emptyMap());
        testContext.setResponse(response);
    }

    @And("^Store The Created Book Id$")
    public void storeBookId() {
    	
    	testContext.setBookId(testContext.getResponse().jsonPath().get("id"));
    	
    }



//    @And("^Verify success response message for registered user$")
//    public void verifySuccessResponseMessageForRegisteredUser() {
//        Assert.assertEquals("FAILED: Unable to verify success response for Register User", "User created successfully", bsBasic.getResponse().jsonPath().get("message"));
//    }



}
