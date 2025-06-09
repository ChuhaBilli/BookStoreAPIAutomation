package com.TestsContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.restassured.response.Response;
import com.pojo.AddBookContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/*
 * Class to hold test data n execution context.
 * This to be used with pico container for parallel execution. 
 */
public class TestContext {

    private static TestContext testContext;
	private String CONFIG_FILE_DIR = "src/test/resources/config/";
	private String DEFAULT_CONFIG_FILE_NAME = "qa_config.properties"; //deafult property file
    private  Config config = null; 
    private Response response = null; 
    private String token = ""; 
    private String tokenType = ""; 
    private String email = ""; 
    private String password = ""; 
    private int bookId = 0;
    private AddBookContract book = null;
    private static final Logger logger = LoggerFactory.getLogger(TestContext.class);




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
    	
    	String  filePath="";
    	Properties properties = new Properties();
    	
    	if( config == null ) {
            config = new Config();
        }
    	
    	/*
    	 * Here the environment parameter from command line is checked,
    	 * then the config file for provided environment is loaded.
    	 * If the config file is not found then the default config file for qa environment is loaded.
    	 */
    	String testEnv = System.getProperty("testEnv");
    	if(testEnv!=null) {
    		
    		String updatedFileName = DEFAULT_CONFIG_FILE_NAME.replace("qa_", testEnv+"_");
    		filePath = CONFIG_FILE_DIR+updatedFileName;
    		if(!new File(filePath).exists()) {
    			filePath = CONFIG_FILE_DIR+DEFAULT_CONFIG_FILE_NAME;
    		}
    		
    		logger.info("Loading config file: "+ filePath);
    	}
    	
    	//Load config
    	try (FileInputStream fis = new FileInputStream(filePath)) {
    		    	
            properties.load(fis);
			config.setUri(properties.getProperty("URI"));
			config.setHealthCheckEndPoint(properties.getProperty("healthCheckEndPoint"));
			config.setLoginEndPoint(properties.getProperty("loginEndPoint"));
			config.setRegistrationEndPoint(properties.getProperty("registrationEndPoint"));
			config.setBooksEndPoint(properties.getProperty("booksEndPoint"));
			config.setBookByIdEndPoint(properties.getProperty("bookByIdEndPoint"));
    		logger.info("Config loaded successfully");
        } catch (IOException e) {
            logger.error(e.toString());
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
