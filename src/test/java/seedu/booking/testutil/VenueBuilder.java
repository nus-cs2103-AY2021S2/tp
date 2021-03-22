package seedu.booking.testutil;


import seedu.booking.model.venue.Venue;

/**
 * A utility class to help with building Venue objects.
 */
public class VenueBuilder {
    public static final String DEFAULT_NAME = "Victoria Hall";
    public static final int DEFAULT_CAPACITY = 50;

    private String name;
    private int capacity;

    /**
     * Creates a {@code VenueBuilder} with the default details.
     */
    public VenueBuilder() {
        name = DEFAULT_NAME;
        capacity = DEFAULT_CAPACITY;
    }

    /**
     * Initializes the VenueBuilder with the data of {@code venueToCopy}.
     */
    public VenueBuilder(Venue venueToCopy) {
        name = venueToCopy.getName();
        capacity = venueToCopy.getCapacity();
    }

    /**
     * Sets the {@code Name} of the {@code Venue} that we are building.
     */
    public VenueBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code Venue} that we are building.
     */
    public VenueBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Venue build() {
        return new Venue(name, capacity);
    }

}
