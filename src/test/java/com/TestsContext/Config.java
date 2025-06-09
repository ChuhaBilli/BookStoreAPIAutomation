package com.TestsContext;

/*
 * Class to hold data from config file.
 * to be expanded as needed.
 */
public class Config {

    private String uri = "http://127.0.0.1:8000";
    private String healthCheckEndPoint = "/health";
    private String loginEndPoint = "/login";
    private String registrationEndPoint = "/signup";
    private String booksEndPoint = "/books/";
    private String bookByIdEndPoint = "/books/";

    
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHealthCheckEndPoint() {
		return healthCheckEndPoint;
	}
	public void setHealthCheckEndPoint(String healthCheckEndPoint) {
		this.healthCheckEndPoint = healthCheckEndPoint;
	}
	public String getLoginEndPoint() {
		return loginEndPoint;
	}
	public void setLoginEndPoint(String loginEndPoint) {
		this.loginEndPoint = loginEndPoint;
	}
	public String getRegistrationEndPoint() {
		return registrationEndPoint;
	}
	public void setRegistrationEndPoint(String registrationEndPoint) {
		this.registrationEndPoint = registrationEndPoint;
	}
	public String getBooksEndPoint() {
		return booksEndPoint;
	}
	public void setBooksEndPoint(String booksEndPoint) {
		this.booksEndPoint = booksEndPoint;
	}
	public String getBookByIdEndPoint() {
		return bookByIdEndPoint;
	}
	public void setBookByIdEndPoint(String bookByIdEndPoint) {
		this.bookByIdEndPoint = bookByIdEndPoint;
	}
    
    
    
}


