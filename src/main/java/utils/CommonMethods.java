package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class CommonMethods {
    Reporter reporter= new Reporter();
    Response response;

    public Response sendGetRequest(String URL){
        try{
            response = RestAssured.get(URL);
            System.out.println("Response: " +response.asString());
        }catch (Exception e){
            System.out.println("Error Message: "+e.getMessage());
        }finally {
            System.out.println("URL: "+URL);
            System.out.println("Status Code: "+response.getStatusCode());
            System.out.println("Response: "+response.asString());
            reporter.addAttachment( URL,response.getStatusCode(),response.asString());
        }
        return response;
    }
}
