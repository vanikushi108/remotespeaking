package com.ca.rs.steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public abstract class AbstractBaseRestApiSteps {


    Response preparePost(String path, String body)
    {
        return null;
    }

    private RequestSpecification preparePostPutWhen(String body) {
       /* return given()
                .port("")
                .auth()
                .basic("", "")
                .contentType(String.valueOf(APPLICATION_JSON))
                .body(body)
                .when();*/
       return null;
    }
}
