package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking/91
       When
           I send GET Request to the url
       Then
           Response body should be like that;
            {
    {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
    */
    @Test
    public void get09() {

        //Set the Url
        spec.pathParams("first","booking","second",91);

        //Set The Expected Data
        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");


        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println(expectedData);

        //Send The Request and Get The Response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        //Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
        assertEquals(bookingdatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));



    }
}
