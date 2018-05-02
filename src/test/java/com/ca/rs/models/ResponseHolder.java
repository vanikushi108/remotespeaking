package com.ca.rs.models;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class ResponseHolder {


    public static Response response;


    public static int responseCode;


    public static String responseBody;

    public static Header responseHeaders;

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        ResponseHolder.response = response;
    }
    public static int getResponseCode() {
        responseCode = response.getStatusCode();
        return responseCode;
    }

    public static void setResponseCode(int responseCode) {
        ResponseHolder.responseCode = responseCode;
    }

    public static String getResponseBody() {
        return responseBody;
    }

    public static void setResponseBody(String responseBody) {
        ResponseHolder.responseBody = responseBody;
    }

    public static Header getResponseHeaders() {
        return responseHeaders;
    }

    public static void setResponseHeaders(Header responseHeaders) {
        ResponseHolder.responseHeaders = responseHeaders;
    }


}