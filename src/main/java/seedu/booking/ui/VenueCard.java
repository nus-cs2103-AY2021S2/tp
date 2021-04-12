package seedu.booking.ui;

import java.util.Comparator;

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
     * Creates a {@code VenueCard} with the given {@code Venue} and index to display.
     */
    public VenueCard(Venue venue, int displayedIndex) {
        super(FXML);
        this.venue = venue;
        populateVenue(venue, displayedIndex);
    }

    /**
     * Creates a {@code VenueCard} with the given {@code FXML}, {@code Venue} and index to display.
     * This is for subclassing to allow different FXML files.
     */
    public VenueCard(String fxml, Venue venue, int displayedIndex) {
        super(fxml);
        this.venue = venue;
        populateVenue(venue, displayedIndex);
    }

    /**
     * Populates {@code VenueCard} with the given {@code Venue} and index to display.
     */
    public void populateVenue(Venue venue, int displayedIndex) {
        id.setText(displayedIndex + ". ");
        name.setText(String.valueOf(venue.getVenueName()));
        capacity.setText(String.valueOf(venue.getCapacity()));
        description.setText(String.valueOf(venue.getDescription()));
        venue.getTags().stream()
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
        if (!(other instanceof VenueCard)) {
            return false;
        }

        // state check
        VenueCard card = (VenueCard) other;
        return id.getText().equals(card.id.getText())
                && venue.equals(card.venue);
    }
}
