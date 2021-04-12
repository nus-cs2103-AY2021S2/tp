package seedu.booking.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.booking.model.booking.Booking;

/**
 * An UI component that displays information of a {@code Booking}.
 */
public class BookingCard extends UiPart<Region> {

    private static final String FXML = "BookingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Booking booking;

    @FXML
    private HBox bookingCardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label booker;
    @FXML
    private Label venue;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code BookingCard} with the given {@code Booking} and index to display.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        populateBooking(booking, displayedIndex);
    }

    /**
     * Creates a {@code BookingCard} with the given {@code FXML}, {@code Booking} and index to display.
     * This is so that subclasses can share the same code but different FXML files
     */
    public BookingCard(String fxml, Booking booking, int displayedIndex) {
        super(fxml);
        this.booking = booking;
        populateBooking(booking, displayedIndex);
    }

    /**
     * Populates {@code BookingCard} with the given {@code Booking} and index to display.
     */
    public void populateBooking(Booking booking, int displayedIndex) {
        id.setText(displayedIndex + ". ");
        title.setText(booking.getVenueName().venueName);
        booker.setText(booking.getBookerEmail().value);
        startTime.setText(booking.getBookingStart().toString());
        endTime.setText(booking.getBookingEnd().toString());
        description.setText(booking.getDescription().value);
        booking.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new TagLabel(tag)));
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
        return id.getText().equals(card.id.getText())
                && booking.equals(card.booking);
    }
}
