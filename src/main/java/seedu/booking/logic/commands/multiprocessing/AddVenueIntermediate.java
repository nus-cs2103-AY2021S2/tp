package seedu.booking.logic.commands.multiprocessing;

import java.util.Set;
import java.util.logging.Logger;

import seedu.booking.MainApp;
import seedu.booking.commons.core.LogsCenter;
import seedu.booking.logic.commands.AddVenueCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

public class AddVenueIntermediate implements Intermediate<AddVenueCommand> {
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private VenueName venueName;
    private Capacity capacity;
    private String description;
    private Set<Tag> tags;

    /**
     * Initialises an AddVenueIntermediate object to store temporary user input.
     */
    public AddVenueIntermediate(VenueName venueName) {
        this.venueName = venueName;
        this.capacity = null;
        this.description = null;
        this.tags = null;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
        logger.info("Intermediate: " + this.capacity.toString());
    }

    public void setDescription(String description) {
        this.description = description;
        logger.info("Intermediate: " + this.description);
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;

        final StringBuilder builder = new StringBuilder();
        if (!this.tags.isEmpty()) {
            this.tags.forEach(builder::append);
        }
        logger.info("Intermediate: " + builder.toString());
    }

    /**
     * Creates a Venue with the existing user input info.
     */
    public Venue createVenue() {
        return new Venue(venueName, capacity, description, tags);
    }

    @Override
    public AddVenueCommand parse() throws ParseException {
        Venue venue = new Venue(venueName, capacity, description, tags);
        return new AddVenueCommand(venue);
    }
}
