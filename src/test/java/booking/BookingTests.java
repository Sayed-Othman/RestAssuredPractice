package booking;

import helpers.BookingHelper;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.request.BookingRequest;
import models.response.BookingResponse;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingTests {

    @Test(description = "Verify that a booking can be created successfully with valid request data.")
    @Description("Verify that a booking can be created successfully with valid request data.")
    public void testCreateBookingSuccessfully() {
        BookingHelper helper = new BookingHelper();
        BookingRequest validRequest = helper.defaultRequest();

        Response response = helper.createBooking(validRequest);
        response.then().statusCode(200).log().all();

        BookingResponse createBookingResponse = response.as(BookingResponse.class);

        // Verify that the firstname in the response matches the request
        assertThat(createBookingResponse.getBooking().getFirstname(), equalTo(validRequest.getFirstname()));
    }

    @Test(description = "Verify that creating a booking without a firstname returns an Internal Server Error (500).")
    @Description("Verify that creating a booking without a firstname returns an Internal Server Error (500).")
    public void testCreateBookingMissingFirstName() {
        BookingHelper helper = new BookingHelper();
        BookingRequest invalidRequest = helper.defaultRequest();
        invalidRequest.setFirstname(null);  // Remove firstname to simulate invalid request

        Response response = helper.createBooking(invalidRequest);

        // Verify that the status code is 500 and the response body contains 'Internal Server Error'
        response.then().statusCode(500);
        assertThat(response.getBody().asString(), equalTo("Internal Server Error"));
    }
}
