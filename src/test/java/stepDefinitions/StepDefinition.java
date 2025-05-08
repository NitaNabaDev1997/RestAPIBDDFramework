package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    static String placeid;
    static String address;
    @Given("Add Place Payload with {string} and {string} and {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        AddPlace p = testDataBuild.AddPlacePayload(name,language,address);

        res = given().spec(requestSpecification())
                .body(p);

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource,String method) {

        APIResources resourcesAPI= APIResources.valueOf(resource);
        System.out.println(resourcesAPI.getResource());

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))
        response = res.when().post(resourcesAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response=res.when().get(resourcesAPI.getResource());
        else if (method.equalsIgnoreCase("PUT")) {
            response=res.when().put(resourcesAPI.getResource());
        }


    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int statuscode) {
        assertEquals(response.statusCode(), statuscode);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedval) {

        assertEquals(getJsonPath(response,key), expectedval);

    }

    @And("verify placeid mapped to {string} as {string} using {string}")
    public void verifyPlaceidMappedToUsing(String key, String expectedName, String resource) throws IOException {

        placeid= getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",placeid);
        user_calls_with_http_request(resource,"GET");
        String actualname=getJsonPath(response,key);
        System.out.println(key);
        System.out.println(expectedName);
        assertEquals(actualname,expectedName);
    }


    @Given("DeletePlace API Payload")
    public void deleteplaceAPIPayload() throws IOException {
        res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayLoad(placeid));

    }

    @Given("Update Place Payload with {string}")
    public void updatePlacePayloadWith(String address) throws IOException {
        res= given().spec(requestSpecification()).body(testDataBuild.updatePlacePayLoad(placeid,address));

    }


    @And("verify {string} is mapped to {string} using {string}")
    public void verifyIsMappedToUsing(String parameter, String expectedName, String resource) throws IOException {

        res = given().spec(requestSpecification()).queryParam("place_id",placeid);
        user_calls_with_http_request(resource,"GET");
        String actualname=getJsonPath(response,parameter);
        System.out.println(actualname);
        assertEquals(actualname,expectedName);
    }
}
