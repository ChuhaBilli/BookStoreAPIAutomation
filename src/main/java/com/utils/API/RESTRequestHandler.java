package com.utils.API;

import com.google.gson.GsonBuilder;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RESTRequestHandler {
	
	/*
	 * REST assured wrapper for CRUD operations
	 */

    Headers headers;
    ContentType contentType;
    String scheme = "http";
    boolean attachLog = true;
    Map<String, String> headersKeyValue = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(RESTRequestHandler.class);


    public RESTRequestHandler() {
        this.contentType = ContentType.JSON;
        List<Header> listHeaders = new ArrayList<>();
        headersKeyValue.forEach((key, value) -> listHeaders.add(new Header(key, value)));
        this.headers = new Headers(listHeaders);
    }

    public RESTRequestHandler(ContentType contentType) {
        this.contentType = contentType;
        List<Header> listHeaders = new ArrayList<>();
        headersKeyValue.forEach((key, value) -> listHeaders.add(new Header(key, value)));
        this.headers = new Headers(listHeaders);
    }

    public RESTRequestHandler(String headerKey, String headerValue, ContentType contentType) {
        this.contentType = contentType;
        List<Header> listHeaders = new ArrayList<>();
        this.headers = new Headers(new Header(headerKey, headerValue));
    }

    public RESTRequestHandler(Map<String, String> mapHeader, ContentType contentType) {
        this.contentType = contentType;
        List<Header> listHeaders = new ArrayList<>();
        mapHeader.forEach((key, value) -> listHeaders.add(new Header(key, value)));
        this.headers = new Headers(listHeaders);
    }

    public Response sendGetRequest(String pathURL, Map<String, ?> queryParams, Object... pathURLParams) {
    	
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all()
                .contentType(contentType).when().get(pathURL, pathURLParams).then().log().all().extract().response();

        if(attachLog) {
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }

    public Response sendGetRequest(String pathURL, Map<String, ?> queryParams, Map<String, ?> pathURLParams) {
    	
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all()
                .contentType(contentType).when().get(pathURL, pathURLParams).then().log().all().extract().response();

        if(attachLog) {
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }


    public Response sendPostRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, Map<String, ?> pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                .when().body(body).post(pathURL, pathURLParams).then().log().all().extract().response();

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }

    public Response sendPostRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, List pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();
        Response response;
        if(pathURLParams.size() == 0) {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).post(pathURL).then().log().all().extract().response();
        } else {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).post(pathURL, pathURLParams.toArray()).then().log().all().extract().response();
        }

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }



    public Response sendPutRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, Map<String, ?> pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                .when().body(body).put(pathURL, pathURLParams).then().log().all().extract().response();

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }

    public Response sendPutRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, List pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();
        Response response;
        if(pathURLParams.size() == 0) {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).put(pathURL).then().log().all().extract().response();
        } else {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).put(pathURL, pathURLParams.toArray()).then().log().all().extract().response();
        }

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }
    

    public Response sendPatchRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, Map<String, ?> pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                .when().body(body).patch(pathURL, pathURLParams).then().log().all().extract().response();

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }

    public Response sendPatchRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, List pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();
        Response response;
        if(pathURLParams.size() == 0) {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).patch(pathURL).then().log().all().extract().response();
        } else {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).patch(pathURL, pathURLParams.toArray()).then().log().all().extract().response();
        }

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }


    public Response sendDeleteRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, Map<String, ?> pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                .when().body(body).delete(pathURL, pathURLParams).then().log().all().extract().response();

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }

    public Response sendDeleteRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathURL, List pathURLParams, Map<String, ?> queryParams) {
        Object body;

        if(!(bodyParams instanceof Number || bodyParams instanceof String)) {
            body = bodyParams != null ? DynamicJsonGenerator.inclusionCheck(bodyParams, inclusionValue): "";
        } else {
            body = bodyParams == null ? "" : bodyParams;
        }

        RequestSpecification requestSpecification = RestAssured.given();
        Response response;
        if(pathURLParams.size() == 0) {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).delete(pathURL).then().log().all().extract().response();
        } else {
            response = requestSpecification.relaxedHTTPSValidation().queryParams(queryParams).headers(headers).request().log().all().contentType(contentType)
                    .when().body(body).delete(pathURL, pathURLParams.toArray()).then().log().all().extract().response();
        }

        if(attachLog) {
            if(body != null) {
                Allure.addAttachment("Request Body - ", contentType.toString(), new GsonBuilder().setPrettyPrinting().create().toJson(new JSONObject(body.toString())));
            }
            Allure.addAttachment("Response Body - ", contentType.toString(), response.getBody().prettyPrint());
        }
        return response;
    }
    
    public Response sendDeleteRequest(String pathURL) {


        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.relaxedHTTPSValidation().headers(headers).request().log().all().contentType(contentType)
                    .when().delete(pathURL).then().log().all().extract().response();
        

        return response;
    }

}
