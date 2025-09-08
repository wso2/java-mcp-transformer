package org.wso2.carbon.mcp.transformer.model;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class ResolvedRequest {

    private Map<String, Object> pathParams = new HashMap<>();
    private Map<String, Object> queryParams = new HashMap<>();
    private Map<String, Object> headerParams = new HashMap<>();
    private JsonObject body;

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, Object> pathParams) {
        this.pathParams = pathParams;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, Object> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(Map<String, Object> headerParams) {
        this.headerParams = headerParams;
    }

    public JsonObject getBody() {
        return body;
    }

    public void setBody(JsonObject body) {
        this.body = body;
    }

}