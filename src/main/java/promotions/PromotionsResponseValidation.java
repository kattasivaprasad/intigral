package promotions;

import io.restassured.response.Response;
import org.junit.Assert;
import pojos.*;
import utils.ProgramType;
import utils.Reporter;

import java.io.IOException;
import java.util.HashMap;

public class PromotionsResponseValidation {
    Reporter reporter=new Reporter();
    private Response response;
    private int statusCode;
    private String errorCode;
    private String errorMessage;
    private HashMap<String, String> channelDetails = new HashMap<>();
    private boolean validationStatus = true;

    public Response getResponseOptions() {
        return response;
    }

    public void setResponseOptions(Response response) {
        this.response = response;
    }

    public boolean isValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HashMap<String, String> getChannelDetails() {
        return channelDetails;
    }

    public void setChannelDetails(HashMap<String, String> channelDetails) {
        this.channelDetails = channelDetails;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public void responseValidation(String statusCode) throws IOException {
        assertEqualCheck("Response status code validation failed ", statusCode, String.valueOf(response.getStatusCode()), "Status Code");
        PromotionsResponse promotionsResponse = response.getBody().as(PromotionsResponse.class);
        assertTrueCheck("Promotions", promotionsResponse.getPromotions() != null);
        promotionsResponse.getPromotions().stream().filter(promotion -> (promotion != null)).forEach(promotion -> {
            try {
                promotionValidation(promotion);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void responseErrorValidation(String statusCode, String errorCode, String errorMessage) throws IOException {
        assertEqualCheck("Response status code validation failed ", statusCode, String.valueOf(response.getStatusCode()), "Status Code");
        String erroResponse=response.getBody().asString();
        System.out.println("Error Response: "+erroResponse);
        assertEqualCheck("Error Code",errorCode,response.jsonPath().getString("error.code"),"Error Code");
        assertEqualCheck("Actual and expected error messages are mismatched", errorMessage, response.jsonPath().getString("error.message"), "Error Message");
        assertTrueCheck("Request id", response.jsonPath().getString("error.requestId") != null);
    }

    public void promotionValidation(Promotion promotion) throws IOException {
        reporter.addAllureAttachment("Promo Type Is: ",promotion.getPromoType());
        assertTrueCheck("Promotion id", promotion.getPromotionId() != null);
        assertTrueCheck("Promotion id", promotion.getPromotionId() instanceof String);
        assertTrueCheck("Order id", promotion.getOrderId() != null);
        assertTrueCheck("Promo area", promotion.getPromoArea() != null);
        assertTrueCheck("Promo type", promotion.getPromoType() != null);
        assertTrueCheck("Show price", promotion.getShowPrice() != null);
        assertTrueCheck("Show price", promotion.getShowPrice() instanceof Boolean);
        assertTrueCheck("Show text", promotion.getShowText() != null);
        assertTrueCheck("Show text", promotion.getShowText() instanceof Boolean);
        assertTrueCheck("Localized Texts", promotion.getLocalizedTexts() != null);
        localizedTextValidation(promotion.getLocalizedTexts());
        assertTrueCheck("Properties", promotion.getProperties() != null);
        promotion.getProperties().stream().filter(property -> (property != null)).forEach(property -> {
            try {
                propertyValidation(property,promotion);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        assertTrueCheck("Images", promotion.getImages() != null);
    }

    public void localizedTextValidation(LocalizedTexts localizedTexts) throws IOException {
        assertTrueCheck("Localized Text-EN", localizedTexts.getEn() != null);
        assertTrueCheck("Localized Text-AR", localizedTexts.getAr() != null);
    }
    public void propertyValidation(Property property, Promotion promotion) throws IOException {
        if(property.getProgramType()!= null) {
            assertTrueCheck("Year", property.getYear() != null);
            assertEqualCheck("Program Type " + property.getProgramType() + " is not mapped to program types " + ProgramType.values().toString(), ProgramType.valueOf(property.getProgramType().toUpperCase()).toString(), property.getProgramType().toUpperCase(), "Program Type " + property.getProgramType());
            assertTrueCheck("Currency", property.getCurrency() != null);
            assertTrueCheck("ProgramAvailabilityId", property.getProgramAvailabilityId() != null);
            assertTrueCheck("Rating", property.getRating() != null);
            assertTrueCheck("Categories", property.getCategories() != null);
            assertTrueCheck("Genre", property.getGenre() != null);
            assertTrueCheck("Program Description", property.getProgramDescription() != null);
            assertTrueCheck("Duration", property.getDuration()!= null);
        }else{
                Assert.assertFalse("Programme Type is not  present for "+promotion.getPromoType()+"", Boolean.parseBoolean(promotion.getPromoType()));
                reporter.addAllureAttachment("Programme Type is not present for " +promotion.getPromoType()+"","false");
            }
        }

    public void assertTrueCheck(String field, boolean val) throws IOException {
        try {
            Assert.assertEquals(field + " field is not present in response", true, val);
            reporter.addAllureAttachment("Parameter " +field + " is Present in response",String.valueOf(val));
        } catch (AssertionError | IOException e) {
            setValidationStatus(false);
            reporter.addAllureAttachment("Parameter " +field + " is Not Present in response",val);
        }
    }

    public void assertEqualCheck(String errorMessage, String expected, String actual, String field) throws IOException {
        try {
            Assert.assertEquals(errorMessage, expected, actual);
            reporter.addAllureAttachment("Parameter " +field.toUpperCase() + " is Present in response as expected",actual);
        } catch (AssertionError | IOException e) {
            setValidationStatus(false);
            reporter.addAllureAttachment("Parameter " +field + " is missing in response",field);
        }
    }
}
