package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
      /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                  {
                     "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "midnight snack"
}
                }
     */

    @Test
    public void get15() {
        //Set the Url
        spec.pathParams("first", "booking", "second", 22);

        //Set the expected Data
        BookingDatesPojo bookingDatesPojo= new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData= new BookingPojo("Josh","Allen",111,true,bookingDatesPojo,"midnight snack");

        //Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());


        //Soft Assertion

        //1.Ad??m Soft assertion objesi olu??tur
        SoftAssert softAssert= new SoftAssert();

        //2. ad??m Assertion yap
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"Firstname uyusmad??");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"totalprice uyusmadi");
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"depositpaid uyusmadi");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"additionalneeds uyusmadi");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), bookingDatesPojo.getCheckin(), "checkin uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), bookingDatesPojo.getCheckout(), "checkout uyusmadi");

        //3. Ad??m: assertAll() methodunu kullan
        softAssert.assertAll();

    }
}
