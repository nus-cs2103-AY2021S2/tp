package seedu.booking.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.booking.model.booking.Booking;

import java.util.Comparator;

/**
 * An UI component that displays more detailed information of a {@code Person}.
 */
public class BookingCardBig extends UiPart<Region> {

    private static final String FXML = "BookingListCardBig.fxml";

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
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label description;
    @FXML
    private Label bookingId;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code BookingCard} with the given {@code Booking} and index to display.
     */
    public BookingCardBig(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(displayedIndex + ". ");
        title.setText(booking.getDescription().value);
        booker.setText(booking.getBookerEmail().value);
        startTime.setText(booking.getBookingStart().toString());
        endTime.setText(booking.getBookingEnd().toString());
        description.setText(booking.getDescription().value);
        //bookingId.setText("ID: " + booking.get.toString());
        booking.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingCardBig)) {
            return false;
        }

        // state check
        BookingCardBig card = (BookingCardBig) other;
        return id.getText().equals(card.id.getText())
                && booking.equals(card.booking);
    }
}
