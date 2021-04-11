package seedu.address.model.person.driver;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Represents a Driver to be attached to a Driver.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Driver extends Person {

    /**
     * Every field must be present and not null. Guaranteed by parent constructor.
     */
    public Driver(Name name, Phone phone) {
        super(name, phone);
    }

    /**
     * Returns true if both drivers have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameDriver(Driver otherDriver) {
        if (otherDriver == this) {
            return true;
        }

        return otherDriver != null
                && otherDriver.getName().equals(getName())
                && otherDriver.getPhone().equals(getPhone());
    }

    /**
     * Returns true if a given string is a valid representation of Driver.
     */
    public static boolean isValidDriver(String driver) throws IllegalValueException {
        requireNonNull(driver);
        /* Driver::toString print in the format of %name; Phone: %phone, therefore length = 1 != 2 means
           the String driver is invalid */

        String[] driverParams = driver.split("; Phone: ");
        if (driverParams.length == 1) {
            return false;
        } else if (!Name.isValidName(driverParams[0])) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        } else if (!Phone.isValidPhone(driverParams[1])) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return true;
    }

    /**
     * Returns true if both drivers have the same identity and data fields.
     * This defines a stronger notion of equality between two drivers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Driver)) {
            return false;
        }

        Driver otherDriver = (Driver) other;
        return otherDriver.getName().equals(getName())
                && otherDriver.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());

        return builder.toString();
    }
}
