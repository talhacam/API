package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;

public class Get06 extends RestfulBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get01() {

        // 1. Set The URL ( URL Olustur)
        spec.pathParams("first","booking","second",2325);

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response ( Talep gonder ve cevap al)

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        //1.Yol
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("John"),
                        "lastname",equalTo("Smith"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"));


        //2.Yol

        JsonPath json= response.jsonPath();
        assertEquals("John",json.getString("firstname"));
        assertEquals("Smith",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertEquals(true,json.getBoolean("depositpaid"));
        assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
        assertEquals("Breakfast",json.getString("additionalneeds"));


        //3.Yol

        //softAssert class 3 adimda kullanılır

        // Obje olusturma

        SoftAssert softAssert= new SoftAssert();

        // Do Assertion

        softAssert.assertEquals(json.getString("firstname"),"John","First Name Hatalı ");
        softAssert.assertEquals(json.getString("lastname"),"Smith","Lastname Hatali");
        softAssert.assertEquals(json.getInt("totalprice"),111,"Total Price hatalı");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"depositpaid hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01","Check in Tarihi Hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01","Chec Out Tarihi Hatali");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast","Additional Needs hatali");
        softAssert.assertAll();

        /* iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
         sagliyoruz.
         Eger islemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz hatalı bile olsa testimiz pass olacaktir.


         */


    }
}
