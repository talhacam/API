package get_request;

import base_url.ReqresBaseUrl;
import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04b extends RestfulBaseUrl {

     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

     */

    @Test
    public void get04() {
//Set the Url
        spec.pathParam("first", "booking").queryParams("firstname", "Almedin", "lastname", "Alikadic");

//Set The Expected Data

//Send The Request and Get The

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));


    }
}
