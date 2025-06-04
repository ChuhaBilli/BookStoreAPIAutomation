package com.TestsContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.restassured.response.Response;
import com.pojo.AddBookContract;



//TODO: this class needs to be used in pico container implementation.
public class TestContext {

    private static TestContext testContext;
	private String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    private  Config config = null; 
    private Response response = null; 
    private String token = ""; 
    private String tokenType = ""; 
    private String email = ""; 
    private String password = ""; 
    private int bookId = 0;
    private AddBookContract book = null;



	private TestContext() {
    }

    public static TestContext getInstance() {
        if( testContext == null ) {
            testContext = new TestContext();
            testContext.loadConfig();
        }
        return testContext;
    }
    
    public Config getConfig() {
		return config;
	}
    
    private void loadConfig() {
    	
    	if( config == null ) {
            config = new Config();
        }
    	
    	Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
			config.setUri(properties.getProperty("URI"));
			config.setHealthCheckEndPoint(properties.getProperty("healthCheckEndPoint"));
			config.setLoginEndPoint(properties.getProperty("loginEndPoint"));
			config.setRegistrationEndPoint(properties.getProperty("registrationEndPoint"));
			config.setBooksEndPoint(properties.getProperty("booksEndPoint"));
			config.setBookByIdEndPoint(properties.getProperty("bookByIdEndPoint"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    

    public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public AddBookContract getBook() {
		return book;
	}

	public void setBook(AddBookContract book) {
		this.book = book;
	}



}
