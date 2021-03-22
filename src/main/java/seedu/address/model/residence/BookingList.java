package seedu.address.model.residence;

public class BookingList {

    public static final String MESSAGE_CONSTRAINTS = "Booking details can take any values, and it should not be blank";
    private String booking;

    /**
     * Constructs a {@code Booking}.
     *
     * @param bookingDetails define the booking time.
     */
    public BookingList(String bookingDetails) {
        this.booking = bookingDetails;
    }

    /**
     * Constructs a default {@code Booking} with no booking.
     */
    public BookingList() {
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

    public String toString() {
        return booking;
    }
}
