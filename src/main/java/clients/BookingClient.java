package clients;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.BookingRequest;

public class BookingClient extends RestAssuredClient {

    public BookingClient(String baseURI) {
        super(baseURI);
    }

    public Response createBooking(String path,BookingRequest bookingRequest) {
        return  postJson(path, null, bookingRequest, ContentType.JSON);
    }
}
