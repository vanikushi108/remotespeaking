package com.ca.rs.steps.api;

import com.ca.rs.http.URLFactory;
import com.ca.rs.models.ResponseHolder;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.formatter.model.DataTableRow;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


/**
 * @author TKRPRASAD on 22/08/2017.
 * This class provides the Step definitions for API Tests
 */
public class ApiTests {

    private Response response;
    private RequestSpecification request;
    private String               url;
    private JsonPath jsonPath;
    private static final Logger LOG = LoggerFactory.getLogger(ApiTests.class);

    @And("^i perform the request$")
    public void iPerformTheRequestAndSetTheResponse() throws Throwable {

        LOG.debug("***Url to hit is***"+this.url);
        response = request.when().get(this.url);
        ResponseHolder.setResponse(response);
    }

    @Then("^the response code should be (\\d+)$")
    public void theResponseCodeShouldBe(int responseCode) throws Throwable {
        Assert.assertEquals(responseCode,ResponseHolder.getResponseCode());
    }

    @And("^response includes the following$")
    public void responseIncludesTheFollowing(DataTable table)  {
        Map<String, String> query = new LinkedHashMap<>();
        LOG.debug("***Response values ***"+" "+response.prettyPrint());
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        LOG.debug("***Json values ***"+" "+jsonPath);
        for(DataTableRow row :table.getGherkinRows()) {
            query.put(row.getCells().get(0),row.getCells().get(1));
        }

        for(List<String> str:table.raw()) {
            for(String key : query.keySet()) {
                Assert.assertEquals(str.get(1),jsonPath.getString(key));
            }
        }
    }

    @And("^I pass the parameters as \"([^\"]*)\" and \"([^\"]*)\" as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPassTheParametersAsAndAsAnd(String key1, String key2, String value1, String value2) throws Throwable {
        request=given().param(key1,value1).param(key2,value2);
    }

    @And("^I verify the body parameter for BUC API contains \"([^\"]*)\"$")
    public void iVerifyTheBodyParameterForBUCAPIContains(String bodyParam) throws Throwable {
        jsonPath = new JsonPath(response.getBody().asString());
        jsonPath.getString(bodyParam);
    }

    @And("^response include the key as \"([^\"]*)\" and value as \"([^\"]*)\"$")
    public void responseIncludeTheKeyAsAndValueAs(String keyParam, String valueParam) throws Throwable {
        Assert.assertEquals(jsonPath.getString(keyParam),valueParam);
    }

    @Given("^a BUC Rollout API is up and running$")
    public void aBUCRolloutAPIIsUpAndRunning() throws Throwable {
        this.url= URLFactory.compileApiBaseUrl();
        response = given().when().get(url);
    }

    @Given("^a endpoint \"([^\"]*)\" exists with in the API$")
    public void aEndpointExistsWithInTheAPI(String endPointParam) throws Throwable {
        this.url += ("/" + endPointParam);
    }
}