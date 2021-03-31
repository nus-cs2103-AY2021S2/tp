package seedu.booking.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.booking.model.Tag;
import seedu.booking.model.util.SampleDataUtil;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * A utility class to help with building Venue objects.
 */
public class VenueBuilder {
    public static final String DEFAULT_NAME = "Victoria Hall";
    public static final int DEFAULT_CAPACITY = 10;
    public static final String DEFAULT_DESCRIPTION = "No description provided.";

    private VenueName name;
    private Capacity capacity;
    private String description;
    private Set<Tag> tags;

    /**
     * Creates a {@code VenueBuilder} with the default details.
     */
    public VenueBuilder() {
        name = new VenueName(DEFAULT_NAME);
        capacity = new Capacity(DEFAULT_CAPACITY);
        description = DEFAULT_DESCRIPTION;
        tags = new HashSet<>();
    }

    /**
     * Initializes the VenueBuilder with the data of {@code venueToCopy}.
     */
    public VenueBuilder(Venue venueToCopy) {
        name = venueToCopy.getVenueName();
        capacity = venueToCopy.getCapacity();
        description = venueToCopy.getDescription();
        tags = new HashSet<>(venueToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Venue} that we are building.
     */
    public VenueBuilder withName(String name) {
        this.name = new VenueName(name);
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code Venue} that we are building.
     */
    public VenueBuilder withCapacity(Integer capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Venue} that we are building.
     */
    public VenueBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Venue} that we are building.
     */
    public VenueBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Venue build() {
        return new Venue(name, capacity, description, tags);
    }

}
