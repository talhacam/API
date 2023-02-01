package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends ReqresBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking/19
       When
            I send GET Request to the URL
        Then
            Status code is 200
        And
            Response body is like:
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
}
    */
    @Test
    public void get12Pojo() {
        //Set the Url
        spec.pathParams("first", "booking", "second", 19);

        //Set the Expected Data
        BookingDatesPojo bookingDatesPojo= new BookingDatesPojo("2018-01-01", "2019-01-01");
        System.out.println(bookingDatesPojo.toString());
        BookingPojo expectedData= new BookingPojo("Dane","Combs",111,true,bookingDatesPojo,"breakfast");
        System.out.println("expectedData=" + expectedData);

        //Send the Request and Get the Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData= response.as(BookingPojo.class);
        System.out.println("actualData=" + actualData);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        //1.Yol
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

        //2.Yol==> tavsiye edilen kısa yol
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());


    }
}
