package seedu.address.testutil;

import seedu.address.model.human.Name;
import seedu.address.model.human.Phone;
import seedu.address.model.human.driver.Driver;

/**
 * A utility class to help with building Person objects.
 */
public class DriverBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";

    private Name name;
    private Phone phone;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public DriverBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public DriverBuilder(Driver personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public DriverBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public DriverBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Driver build() {
        return new Driver(name, phone);
    }

}
