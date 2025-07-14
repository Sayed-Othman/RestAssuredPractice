package booking;

import helpers.BookingHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.request.BookingRequest;
import models.response.BookingResponse;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookingTests {

    @Test(description = "Verify that a booking can be created successfully with valid request data.")
    @Description("Verify that a booking can be created successfully with valid request data.")
    public void testCreateBookingSuccessfully() {

        Allure.step("Prepare valid booking request");
        BookingHelper bookingHelper = new BookingHelper();
        BookingRequest validRequest = bookingHelper.defaultRequest();

        Allure.step("Send POST request to create booking");
        Response response = bookingHelper.createBooking(validRequest);
        response.then().statusCode(200).log().all();

        Allure.step("Deserialize response into BookingResponse model");
        BookingResponse createBookingResponse = response.as(BookingResponse.class);

        Allure.addAttachment("Returned firstname", createBookingResponse.getBooking().getFirstname());
        Allure.addAttachment("Expected firstname", validRequest.getFirstname());

        Allure.step("Assert that returned firstname matches the request");
        assertThat(createBookingResponse.getBooking().getFirstname(), equalTo(validRequest.getFirstname()));
    }

    @Test(description = "Verify that creating a booking without a firstname returns an Internal Server Error (500).")
    @Description("Verify that creating a booking without a firstname returns an Internal Server Error (500).")
    public void testCreateBookingMissingFirstName() {

        Allure.step("Prepare booking request with missing firstname");
        BookingHelper bookingHelper = new BookingHelper();
        BookingRequest invalidRequest = bookingHelper.defaultRequest();
        invalidRequest.setFirstname(null);  // Remove firstname to simulate invalid request

        Allure.step("Send POST request with invalid booking request");
        Response response = bookingHelper.createBooking(invalidRequest);

        Allure.addAttachment("Response body", response.getBody().asString());

        Allure.step("Assert that status code is 500");
        response.then().statusCode(500);

        Allure.step("Assert that response body is 'Internal Server Error'");
        assertThat(response.getBody().asString(), equalTo("Internal Server Error"));
    }
}
