package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;

public class BookingCard extends UiPart<Region> {

    private static final String FXML = "ResidenceListCard.fxml";

    public final Booking booking;

    @FXML
    private HBox bookingCardPane;
    @FXML
    private Label bookingId;
    @FXML
    private Label bookingDetails;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        bookingId.setText(displayedIndex + ". ");
        bookingDetails.setText(booking.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingCard)) {
            return false;
        }

        // state check
        BookingCard card = (BookingCard) other;
        return bookingId.getText().equals(card.bookingId.getText())
                && booking.equals(card.booking);
    }
}
