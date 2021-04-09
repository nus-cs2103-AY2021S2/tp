package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;

/**
 * A utility class to help with building Driver objects.
 */
public class DriverBuilder {

    public static final String DEFAULT_NAME = "Dr Iver Man";
    public static final String DEFAULT_PHONE = "85355255";

    private Name name;
    private Phone phone;

    /**
     * Creates a {@code DriverBuilder} with the default details.
     */
    public DriverBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
    }

    /**
     * Initializes the DriverBuilder with the data of {@code passengerToCopy}.
     */
    public DriverBuilder(Driver passengerToCopy) {
        name = passengerToCopy.getName();
        phone = passengerToCopy.getPhone();
    }

    /**
     * Sets the {@code Name} of the {@code Driver} that we are building.
     */
    public DriverBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Driver} that we are building.
     */
    public DriverBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Driver build() {
        return new Driver(name, phone);
    }

}
