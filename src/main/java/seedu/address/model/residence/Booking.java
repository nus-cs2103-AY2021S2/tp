package seedu.address.model.residence;

public class Booking {

    public static final String MESSAGE_CONSTRAINTS = "Booking details can take any values, and it should not be blank";
    private String booking;

    /**
     * Constructs a {@code Booking}.
     *
     * @param bookingDetails define the booking time.
     */
    public Booking(String bookingDetails) {
        this.booking = bookingDetails;
    }

    /**
     * Constructs a default {@code Booking} with no booking.
     */
    public Booking() {
        this.booking = "No Booking";
    }

    /**
     * Constructs a {@code Booking}.
     */
    public static boolean isValidBooking(String trimmedBooking) {
        return true;
    }

    /**
     * Returns value of this {@code Booking}.
     */
    public String getValue() {
        return booking;
    }
}
