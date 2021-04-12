package seedu.booking.ui;

import seedu.booking.model.venue.Venue;

/**
 * An UI component that displays more detailed information of a {@code Venue}.
 */
public class VenueCardBig extends VenueCard {

    private static final String FXML = "VenueListCardBig.fxml";

    /**
     * Creates a {@code VenueCardBig} with the given {@code Venue} and index to display.
     */
    public VenueCardBig(Venue venue) {
        super(FXML, venue, -1);
    }
}
