package seedu.booking.ui;

import seedu.booking.model.booking.Booking;
/**
 * An UI component that displays more detailed information of a {@code Booking}.
 */
public class BookingCardBig extends BookingCard {

    private static final String FXML = "BookingListCardBig.fxml";

    /**
     * Creates a {@code BookingCardBig} with the given {@code Booking} and index to display.
     */
    public BookingCardBig(Booking booking) {
        super(FXML, booking, -1);
    }
}
