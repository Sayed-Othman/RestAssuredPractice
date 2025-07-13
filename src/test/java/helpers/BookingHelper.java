package helpers;

import clients.RestAssuredClient;
import config.APIConstant;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.BookingRequest;
import utils.JsonReader;

public class BookingHelper {

    private final RestAssuredClient client = new RestAssuredClient(APIConstant.BASE_URL);

    public BookingRequest defaultRequest() {
        String jsonFilePath = "src/test/resources/booking.json";
        return JsonReader.readJson(jsonFilePath, BookingRequest.class);
    }

    public Response createBooking(BookingRequest request) {
        return client.postJson(APIConstant.Booking.CREATE_BOOKING, null, request, ContentType.JSON);
    }
}
