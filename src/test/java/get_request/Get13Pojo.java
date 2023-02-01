package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            {
    "meta": null,
    "data": {
        "id": 2508,
        "name": "Anaadi Malik",
        "email": "anaadi_malik@lang.name",
        "gender": "female",
        "status": "inactive"
    }
}
          }
    */

    @Test
    public void get13Pojo() {
        //Set the Url
        spec.pathParams("first","users","second",2508);

        //Set the Expected Data
        GoRestDataPojo goRestDataPojo= new GoRestDataPojo(2508,"Anaadi Malik","anaadi_malik@lang.name","female","inactive");
        GoRestPojo expectedData= new GoRestPojo(null, goRestDataPojo);

        //Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        GoRestPojo actualData= response.as(GoRestPojo.class);
        System.out.println("actualData= " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(goRestDataPojo.getId(),actualData.getData().getId());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());






    }
}
