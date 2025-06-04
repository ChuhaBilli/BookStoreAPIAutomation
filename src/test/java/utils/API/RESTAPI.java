package utils.API;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RESTAPI extends RESTRequestHandler {
	
	
	//TODO: to be removed, there is no need for this class and these methods can be directly called from step defs

    public RESTAPI() {
        super();
    }

    public RESTAPI(ContentType contentType) {
        super(contentType);
    }

    public RESTAPI(String headerKey, String headerValue, ContentType contentType) {
        super(headerKey, headerValue, contentType);
    }

    public RESTAPI(String headerKey, String headerValue) {
        super(headerKey, headerValue, ContentType.JSON);
    }

    public RESTAPI(Map<String, String> mapHeaders, ContentType contentType) {
        super(mapHeaders, contentType);
    }

    public RESTAPI(Map<String, String> mapHeaders) {
        super(mapHeaders, ContentType.JSON);
    }


//moved
    public Response getRequest(String pathUrl, Object... pathUrlParams) {
        return sendGetRequest(pathUrl, Collections.emptyMap(), pathUrlParams);
    }

    public Response getRequest(String pathUrl, Map<String, ?> queryParams) {
        return sendGetRequest(pathUrl, queryParams);
    }

    public Response getRequest(String pathUrl, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return getRestAPIResponse(pathUrl, pathParams, queryParams);
    }



    public Response postRequest(String pathUrl) {
        return postRestAPIResponse(null, null, pathUrl, Collections.emptyMap(), Collections.emptyMap());
    }

    public Response postRequest(String pathUrl, List pathParams) {
        return sendPostRequest(null, null, pathUrl, pathParams, Collections.emptyMap());
    }

    public Response postRequest(String pathUrl, Map<String, ?> queryParams) {
        return postRestAPIResponse(null, null, pathUrl, Collections.emptyMap(), queryParams);
    }

    public Response postRequest(String pathUrl, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return postRestAPIResponse(null, null, pathUrl, pathParams, queryParams);
    }

    public Response postRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl) {
        return sendPostRequest(bodyParams, inclusionValue, pathUrl, Collections.emptyList(), Collections.emptyMap());
    }

    //TODO: usage for this moved, review and remove
    public Response postRequest(Object bodyParams, String pathUrl) {
        return sendPostRequest(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, Collections.emptyList(), Collections.emptyMap());
    }

    public Response postRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, Map<String, ?> queryParams) {
        return sendPostRequest(bodyParams, inclusionValue, pathUrl, Collections.emptyList(), queryParams);
    }

    public Response postRequest(Object bodyParams, String pathUrl, Map<String, ?> queryParams) {
        return sendPostRequest(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, Collections.emptyList(), queryParams);
    }

    public Response postRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, List pathUrlParams) {
        return sendPostRequest(bodyParams, inclusionValue, pathUrl, pathUrlParams, Collections.emptyMap());
    }

    public Response postRequest(Object bodyParams, String pathUrl, List pathUrlParams) {
        return sendPostRequest(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, pathUrlParams, Collections.emptyMap());
    }

    public Response postRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, Map<String, ?> pathUrlParams, Map<String, ?> queryParams) {
        return postRestAPIResponse(bodyParams, inclusionValue, pathUrl, pathUrlParams, queryParams);
    }

    public Response postRequest(Object bodyParams, String pathUrl, Map<String, ?> pathUrlParams, Map<String, ?> queryParams) {
        return postRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, pathUrlParams, queryParams);
    }

    public Response patchRequest(String pathUrl) {
        return patchRestAPIResponse(null, null, pathUrl, Collections.emptyMap(), Collections.emptyMap());
    }

    public Response patchRequest(String pathUrl, List pathParams) {
        return patchRestAPIResponse(null, null, pathUrl, pathParams, Collections.emptyMap());
    }

    public Response patchRequest(String pathUrl, Map<String, ?> queryParams) {
        return patchRestAPIResponse(null, null, pathUrl, Collections.emptyMap(), queryParams);
    }

    public Response patchRequest(String pathUrl, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return patchRestAPIResponse(null, null, pathUrl, pathParams, queryParams);
    }

    public Response patchRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl) {
        return patchRestAPIResponse(bodyParams, inclusionValue, pathUrl, Collections.emptyList(), Collections.emptyMap());
    }

    public Response patchRequest(Object bodyParams, String pathUrl) {
        return patchRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, Collections.emptyList(), Collections.emptyMap());
    }

    public Response patchRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, Map<String, ?> queryParams) {
        return patchRestAPIResponse(bodyParams, inclusionValue, pathUrl, Collections.emptyList(), queryParams);
    }

    public Response patchRequest(Object bodyParams, String pathUrl, Map<String, ?> queryParams) {
        return patchRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, Collections.emptyList(), queryParams);
    }

    public Response patchRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, List pathUrlParams) {
        return patchRestAPIResponse(bodyParams, inclusionValue, pathUrl, pathUrlParams, Collections.emptyMap());
    }

    public Response patchRequest(Object bodyParams, String pathUrl, List pathUrlParams) {
        return patchRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, pathUrlParams, Collections.emptyMap());
    }

    public Response patchRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, Map<String, ?> pathUrlParams, Map<String, ?> queryParams) {
        return patchRestAPIResponse(bodyParams, inclusionValue, pathUrl, pathUrlParams, queryParams);
    }

    public Response patchRequest(Object bodyParams, String pathUrl, Map<String, ?> pathUrlParams, Map<String, ?> queryParams) {
        return patchRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, pathUrlParams, queryParams);
    }



    public Response deleteRequest(String pathUrl) {
        return deleteRestAPIResponse(null, null, pathUrl, Collections.emptyMap(), Collections.emptyMap());
    }

    public Response deleteRequest(String pathUrl, List pathParams) {
        return deleteRestAPIResponse(null, null, pathUrl, pathParams, Collections.emptyMap());
    }

    public Response deleteRequest(String pathUrl, Map<String, ?> queryParams) {
        return deleteRestAPIResponse(null, null, pathUrl, Collections.emptyMap(), queryParams);
    }

    public Response deleteRequest(String pathUrl, Map<String, ?> pathParams, Map<String, ?> queryParams) {
        return deleteRestAPIResponse(null, null, pathUrl, pathParams, queryParams);
    }

    public Response deleteRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl) {
        return deleteRestAPIResponse(bodyParams, inclusionValue, pathUrl, Collections.emptyList(), Collections.emptyMap());
    }

    public Response deleteRequest(Object bodyParams, String pathUrl) {
        return deleteRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, Collections.emptyList(), Collections.emptyMap());
    }

    public Response deleteRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, Map<String, ?> queryParams) {
        return deleteRestAPIResponse(bodyParams, inclusionValue, pathUrl, Collections.emptyList(), queryParams);
    }

    public Response deleteRequest(Object bodyParams, String pathUrl, Map<String, ?> queryParams) {
        return deleteRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, Collections.emptyList(), queryParams);
    }

    public Response deleteRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, List pathUrlParams) {
        return deleteRestAPIResponse(bodyParams, inclusionValue, pathUrl, pathUrlParams, Collections.emptyMap());
    }

    public Response deleteRequest(Object bodyParams, String pathUrl, List pathUrlParams) {
        return deleteRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, pathUrlParams, Collections.emptyMap());
    }

    public Response deleteRequest(Object bodyParams, DynamicJsonGenerator.inclusion inclusionValue, String pathUrl, Map<String, ?> pathUrlParams, Map<String, ?> queryParams) {
        return deleteRestAPIResponse(bodyParams, inclusionValue, pathUrl, pathUrlParams, queryParams);
    }

    public Response deleteRequest(Object bodyParams, String pathUrl, Map<String, ?> pathUrlParams, Map<String, ?> queryParams) {
        return deleteRestAPIResponse(bodyParams, DynamicJsonGenerator.inclusion.EXCLUDE_NULL, pathUrl, pathUrlParams, queryParams);
    }
}
