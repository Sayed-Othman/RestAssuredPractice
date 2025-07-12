package models.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookingRequest {

    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
