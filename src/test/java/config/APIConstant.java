package config;

public class APIConstant {

    public static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public static class Booking {
        public static final String CREATE_BOOKING = "/booking";
        public static final String GET_BOOKING = "/booking/{id}";
        public static final String DELETE_BOOKING = "/booking/{id}";
    }

    public static class Auth {
        public static final String LOGIN = "/auth";
    }
}
