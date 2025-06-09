package com.stepsDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.pojo.AddBookContract;
import com.pojo.SignupContract;
import com.pojo.UserLoginContract;
import com.TestsContext.Config;
import com.TestsContext.TestContext;
import com.utils.CommonUtils;
import com.utils.API.DynamicJsonGenerator;
import com.utils.API.RESTRequestHandler;
import org.junit.Assert;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepsDef_BookStore {

	TestContext testContext = TestContext.getInstance();
	Config config = testContext.getConfig();
	RESTRequestHandler reqHandler = new RESTRequestHandler();

	// Common
	@Then("^Verify response status code is \"([^\"]*)\"$")
	public void validateResponseStatusCode(int expectedStatusCode) {

		int actualStatusCode = testContext.getResponse().getStatusCode();
		Assert.assertEquals("FAILED: Unable to verify expected status code -" + expectedStatusCode
				+ ", Actual Status code -" + actualStatusCode, expectedStatusCode, actualStatusCode);
	}

//    @Given("^Set user details$")
//    public void createSetUserDetails() {
//    	
//    	
//        String randomEmail = CommonUtils.randomString(6, true, false) + "@" + CommonUtils.randomString(5, true, false) + ".com";
//        String password = "123456";
//        
//        //Registration Request
//        SignupContract signupContract = SignupContract.builder()
//                .email(randomEmail).password(password).build();
//        String url = config.getUri()+config.getRegistrationEndPoint();
//        Response response = reqHandler.sendPostRequest(signupContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL,url , Collections.emptyList(), Collections.emptyMap());
//        
//        //Login Request
//        UserLoginContract userLoginContract = UserLoginContract.builder()
//                .email(randomEmail).password(password).build();
//        url = config.getUri()+config.getLoginEndPoint();
//        
//        response = reqHandler.sendPostRequest(userLoginContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url, Collections.emptyList(), Collections.emptyMap());
//        testContext.setResponse(response);
//
//    }

	// Health check
	@When("^Send GET request to health check endpoint$")
	public void sendGetRequestToHealthCheckEndpoint() {

		String url = config.getUri() + config.getHealthCheckEndPoint();
		Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
		testContext.setResponse(response);
	}

	@And("^Verify response message for health check$")
	public void verifyResponseMessageForHealthCheck() {

		Assert.assertEquals("FAILED: response message mismatch", "up",
				testContext.getResponse().jsonPath().get("status"));
	}

	// Register user
	@When("Send POST request to signup endpoint with values {string} and {string}")
	public void send_post_request_to_signup_endpoint_with_values_and(String email, String pswd) {

					
		
		if (email.equals("@testContext")) {
			email = testContext.getEmail();
		} else if (email.equals("null")) {
			email = "";
		} else {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
			String formattedDateTime = now.format(formatter);
			email = formattedDateTime + email;
		}

		if (pswd.equals("null")) {
			pswd = "";
		}
		testContext.setEmail(email);
		testContext.setPassword(pswd);

		String url = config.getUri() + config.getRegistrationEndPoint();
		SignupContract signupContract = SignupContract.builder().email(email).password(pswd).build();
		Response response = reqHandler.sendPostRequest(signupContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url,
				Collections.emptyList(), Collections.emptyMap());
		testContext.setResponse(response);

	}

	@And("^Verify success response message for registered user$")
	public void verifySuccessResponseMessageForRegisteredUser() {
		Assert.assertEquals("FAILED: Unable to verify success response for Register User", "User created successfully",
				testContext.getResponse().jsonPath().get("message"));
	}

	@Given("Verify error in response message for already registered user")
	public void verify_error_in_response_message_for_already_registered_user() {
		
		Assert.assertEquals("FAILED: error message mismatch", "Email already registered",
				testContext.getResponse().jsonPath().get("detail"));
		
	}

	// Login User with email and password provided
	@When("Send POST request to login endpoint with values {string} and {string}")
	public void send_post_request_to_login_endpoint_with_values_and(String email, String pswd) {

		UserLoginContract userLoginContract = UserLoginContract.builder().email(email).password(pswd).build();
		String url = config.getUri() + config.getLoginEndPoint();
		Response response = reqHandler.sendPostRequest(userLoginContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL,
				url, Collections.emptyList(), Collections.emptyMap());
		testContext.setResponse(response);
		testContext.setEmail(email);
		testContext.setPassword(pswd);
	}

	// Login user with email already registered.
	@When("Send POST request to login endpoint using registered user credentials")
	public void send_post_request_to_login_endpoint_using_registered_user_credentials() {

		String email = testContext.getEmail();
		String pswd = testContext.getPassword();
		UserLoginContract userLoginContract = UserLoginContract.builder().email(email).password(pswd).build();
		String url = config.getUri() + config.getLoginEndPoint();
		Response response = reqHandler.sendPostRequest(userLoginContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL,
				url, Collections.emptyList(), Collections.emptyMap());
		testContext.setResponse(response);
	}
	
	@Then("Verify response message for invalid credentials")
	public void verify_response_message_for_invalid_credentials() {
		
		Assert.assertEquals("FAILED: response message mismatch", "Incorrect email or password",
				testContext.getResponse().jsonPath().get("detail"));
	  
	}

	@Given("Store access token")
	public void store_access_token() {

		testContext.setToken(testContext.getResponse().jsonPath().get("access_token"));
		testContext.setTokenType(testContext.getResponse().jsonPath().get("token_type"));
	}

	// Add book
	@When("Send POST request to add book with details {string}, {string}, {string}, {string}")
	public void send_post_request_to_add_book_with_details(String bookName, String author, String publishedYear,
			String bookSummary) {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
		String formattedDateTime = now.format(formatter);

		bookName = bookName + formattedDateTime;
		author = author + formattedDateTime;
		publishedYear = publishedYear + formattedDateTime;
		bookSummary = bookSummary + formattedDateTime;

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		AddBookContract addBookContract = AddBookContract.builder().name(bookName).author(author)
				.published_year(publishedYear).book_summary(bookSummary).build();
		String url = config.getUri() + config.getBooksEndPoint();

		// TODO review this. instead create update headers method in Request Handler
		// Class.
		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendPostRequest(addBookContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL,
				url, Collections.emptyList(), Collections.emptyMap());
		testContext.setResponse(response);
		testContext.setBook(addBookContract);

	}

	@And("^Store The Created Book Id$")
	public void storeBookId() {

		testContext.setBookId(testContext.getResponse().jsonPath().get("id"));

	}

	@When("Send GET request to book endpoint with id parameter of book created")
	public void send_get_request_to_book_endpoint_with_id_parameter_of_book_created() {

		String url = config.getUri() + config.getBookByIdEndPoint() + testContext.getBookId();
		Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
		testContext.setResponse(response);
	}

	@Then("Verify the added book details with details in response")
	public void verify_the_added_book_details_with_details_in_response() {

		AddBookContract bookAdded = testContext.getBook();
		Response response = testContext.getResponse();
		Assert.assertEquals("FAILED: Book name mismatch", bookAdded.getName(), response.jsonPath().get("name"));
		Assert.assertEquals("FAILED: Published year mismatch", bookAdded.getPublished_year(),
				response.jsonPath().get("published_year").toString());
		Assert.assertEquals("FAILED: Author mismatch", bookAdded.getAuthor(), response.jsonPath().get("author"));
		Assert.assertEquals("FAILED: Summary mismatch", bookAdded.getBook_summary(),
				response.jsonPath().get("book_summary"));

	}
	
	@When("Send GET request to book endpoint with invalid id")
	public void send_get_request_to_book_endpoint_with_invalid_id() {
			
		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
		String formattedDateTime = now.format(formatter);

		String url = config.getUri() + config.getBookByIdEndPoint() + formattedDateTime;

		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
		testContext.setResponse(response);
	}
	
	@Given("Verify response message for book not found")
	public void verify_response_message_for_book_not_found() {
		
		Assert.assertEquals("FAILED: Message mismatch", "Book not found",
				testContext.getResponse().jsonPath().get("detail"));
		
	}

	@When("Send PUT request to book endpoint with id parameter with update book data")
	public void send_put_request_to_book_endpoint_with_id_parameter_with_update_book_data() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
		String formattedDateTime = now.format(formatter);

		String bookName = "UpdatedName" + formattedDateTime;
		String author = "UpdatedAuthor" + formattedDateTime;
		String publishedYear = "1947";
		String bookSummary = "UpdatedSummary" + formattedDateTime;

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		AddBookContract addBookContract = AddBookContract.builder().name(bookName).author(author)
				.published_year(publishedYear).book_summary(bookSummary).build();
		String url = config.getUri() + config.getBookByIdEndPoint() + testContext.getBookId();

		// TODO review this. instead create update headers method in Request Handler
		// Class.
		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendPutRequest(addBookContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url,
				Collections.emptyList(), Collections.emptyMap());
		testContext.setResponse(response);
		testContext.setBook(addBookContract);

	}
	
	@When("Send PUT request to book endpoint with invalid id parameter with update book data")
	public void send_put_request_to_book_endpoint_with_invalid_id_parameter_with_update_book_data() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
		String formattedDateTime = now.format(formatter);

		String bookName = "UpdatedName" + formattedDateTime;
		String author = "UpdatedAuthor" + formattedDateTime;
		String publishedYear = "1947";
		String bookSummary = "UpdatedSummary" + formattedDateTime;

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		AddBookContract addBookContract = AddBookContract.builder().name(bookName).author(author)
				.published_year(publishedYear).book_summary(bookSummary).build();
		String url = config.getUri() + config.getBookByIdEndPoint() + formattedDateTime;

		// TODO review this. instead create update headers method in Request Handler
		// Class.
		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendPutRequest(addBookContract, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, url,
				Collections.emptyList(), Collections.emptyMap());
		testContext.setResponse(response);
		testContext.setBook(addBookContract);

	}

	@When("Send GET request to book endpoint to get list of all the books")
	public void send_get_request_to_book_endpoint_to_get_list_of_all_the_books() {

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		String url = config.getUri() + config.getBooksEndPoint();
		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
		testContext.setResponse(response);

	}
	
	@When("Send GET request to book endpoint with invalid access token")
	public void send_get_request_to_book_endpoint_with_invalid_access_token() {
	   
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + "abcd1234");
		headersKeyValue.put("Connection", "keep-alive");

		String url = config.getUri() + config.getBooksEndPoint();
		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
		testContext.setResponse(response);
		
	}
	
	@Given("Verify response message for invalid token")
	public void verify_response_message_for_invalid_token() {
		Assert.assertEquals("FAILED: response message mismatch", "Invalid token or expired token",
				testContext.getResponse().jsonPath().get("detail"));
	}

	@Given("Verify the added book details with details in get all response")
	public void verify_the_added_book_details_with_details_in_get_all_response() {

		AddBookContract bookAdded = testContext.getBook();
		Response response = testContext.getResponse();

		List<Map<String, Object>> booksList = response.jsonPath().getList("$");
		Map<String, Object> bookInResponse = null;
		Assert.assertTrue(booksList.size() > 0);
		int bookIdToFind = testContext.getBookId();
		for(Map<String, Object> book : booksList) {
			if(book.get("id").equals(bookIdToFind)) {
				bookInResponse = book;
			}
		}
		
		Assert.assertNotNull(bookInResponse);
		Assert.assertEquals("FAILED: Book name mismatch", bookAdded.getName(), bookInResponse.get("name"));
		Assert.assertEquals("FAILED: Published year mismatch", bookAdded.getPublished_year(),
				bookInResponse.get("published_year").toString());
		Assert.assertEquals("FAILED: Author mismatch", bookAdded.getAuthor(), bookInResponse.get("author"));
		Assert.assertEquals("FAILED: Summary mismatch", bookAdded.getBook_summary(), bookInResponse.get("book_summary"));

	}
	
	
	@When("Send GET request to book endpoint with null access token")
	public void send_get_request_to_book_endpoint_with_null_access_token() {
	    
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer ");
		headersKeyValue.put("Connection", "keep-alive");

		String url = config.getUri() + config.getBooksEndPoint();
		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendGetRequest(url, Collections.emptyMap());
		testContext.setResponse(response);
		
	}
	
	@Given("Verify response message for not authenticated")
	public void verify_response_message_for_not_authenticated() {
		Assert.assertEquals("FAILED: response message mismatch", "Not authenticated",
				testContext.getResponse().jsonPath().get("detail"));
	}
	
	@When("Send DELETE request to books endpoint with book id")
	public void send_delete_request_to_book_endpoint_with_book_id() {

		

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		String url = config.getUri() + config.getBookByIdEndPoint() + testContext.getBookId();

		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendDeleteRequest(url);
		testContext.setResponse(response);

	}
	
	@Given("Verify the deleted book message in response")
	public void verify_deleted_book_message() {
		
		Assert.assertEquals("FAILED: Message mismatch", "Book deleted successfully",
				testContext.getResponse().jsonPath().get("message"));
		
	}
	
	@When("Send DELETE request to books endpoint with invalid book id")
	public void send_delete_request_to_book_endpoint_with_invalid_book_id() {

		

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		String url = config.getUri() + config.getBookByIdEndPoint() + "0";

		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendDeleteRequest(url);
		testContext.setResponse(response);

	}
	
	@When("Send DELETE request to books endpoint for all books")
	public void send_delete_request_to_book_endpoint_for_all_books() {

		String token = testContext.getToken();
		Map<String, String> headersKeyValue = new HashMap<>();
		headersKeyValue.put("Authorization", "Bearer " + token);
		headersKeyValue.put("Connection", "keep-alive");

		String url = config.getUri() + config.getBookByIdEndPoint();

		this.reqHandler = new RESTRequestHandler(headersKeyValue, ContentType.JSON);
		Response response = reqHandler.sendDeleteRequest(url);
		testContext.setResponse(response);

	}
	
	@Then("Verify response message for method not allowed")
	public void verify_response_message_for_method_not_allowed() {
		
		Assert.assertEquals("FAILED: Message mismatch", "Method Not Allowed",
				testContext.getResponse().jsonPath().get("detail"));
		
	}


}
