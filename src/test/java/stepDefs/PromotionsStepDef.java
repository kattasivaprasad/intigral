package stepDefs;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import org.junit.Assert;
import promotions.PromotionsResponseValidation;
import utils.CommonMethods;
import utils.Reporter;

import java.io.IOException;

public class PromotionsStepDef {

    PromotionsResponseValidation responseValidation = new PromotionsResponseValidation();
    CommonMethods commonMethods = new CommonMethods();
    Response response;

    @When("I get a list of promotions using API key {string},{string},{string}")
    public void i_get_a_list_of_promotions_using_api_key(String url, String method, String apiKey) throws IOException {
        String urlWithoutKey=url;
        String URL=urlWithoutKey+"?apikey=" + apiKey +"";
        System.out.println("URL: "+URL);
        response = commonMethods.sendGetRequest(URL);
        Assert.assertEquals("Response is received", true, response.getBody().toString().length() > 0);
    }

    @Then("I validate the response {string}")
    public void i_validate_the_response(String statusCode) throws IOException {
        responseValidation.setResponseOptions(response);
        responseValidation.responseValidation(statusCode);
        Assert.assertEquals("Response Validation is failed", true, responseValidation.isValidationStatus());
    }

    @Then("I validate the error response {string},{string},{string}")
    public void iValidateTheErrorResponse(String statusCode, String errorCode, String errorMessage) throws IOException {
        responseValidation.setResponseOptions(response);
        responseValidation.responseErrorValidation(statusCode, errorCode,errorMessage);
        Assert.assertEquals("Response Error Validation is failed", true, responseValidation.isValidationStatus());
    }
}
