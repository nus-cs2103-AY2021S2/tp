package seedu.address.model.residence;

public class BookingDetails {

    private String booking;

    /**
     * Constructs a {@code BookingDetails}.
     *
     * @param bookingDetails define the booking time.
     */
    public BookingDetails(String bookingDetails) {
        this.booking = bookingDetails;
    }

    /**
     * Constructs a default {@code BookingDetails} with no booking.
     */
    public BookingDetails() {
        this.booking = "No Booking";
    }

    /**
     * Constructs a {@code BookingDetails}.
     */
    public static boolean isValidBooking(String trimmedBooking) {
        return true;
    }

    /**
     * Returns value of this {@code BookingDetails}.
     */
    public String getValue() {
        return booking;
    }
}
