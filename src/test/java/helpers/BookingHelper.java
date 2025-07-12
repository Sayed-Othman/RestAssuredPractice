package helpers;

import clients.BookingClient;
import config.APIConstant;
import io.restassured.response.Response;
import models.request.BookingRequest;
import utils.JsonReader;

public class BookingHelper {

    private final BookingClient client = new BookingClient(APIConstant.BASE_URL);

    /**
     * Loads booking request data from a JSON file.
     *
     * @return BookingRequest object loaded from booking.json
     */
    public BookingRequest defaultRequest() {
        String jsonFilePath = "src/test/resources/booking.json";
        return JsonReader.readJson(jsonFilePath, BookingRequest.class);
    }

    public Response createBooking(BookingRequest request) {
        return client.createBooking(APIConstant.Booking.CREATE_BOOKING, request);
    }
}
