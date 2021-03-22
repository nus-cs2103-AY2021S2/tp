package seedu.booking.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.booking.model.venue.Venue;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class VenueCard extends UiPart<Region> {

    private static final String FXML = "VenueListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Venue venue;

    @FXML
    private HBox bookingCardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label capacity;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code BookingCard} with the given {@code Booking} and index to display.
     */
    public VenueCard(Venue venue, int displayedIndex) {
        super(FXML);
        this.venue = venue;
        id.setText(displayedIndex + ". ");
        name.setText(String.valueOf(venue.getVenueName()));
        capacity.setText(String.valueOf(venue.getCapacity()));
        /* person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName))); */
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VenueCard)) {
            return false;
        }

        // state check
        VenueCard card = (VenueCard) other;
        return id.getText().equals(card.id.getText())
                && venue.equals(card.venue);
    }
}
