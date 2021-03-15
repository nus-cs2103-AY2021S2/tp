package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.booking.Venue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class VenueBuilder {

    public static final String DEFAULT_NAME = "Victoria Hall";
    public static final int DEFAULT_CAPACITY = 10;

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
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
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