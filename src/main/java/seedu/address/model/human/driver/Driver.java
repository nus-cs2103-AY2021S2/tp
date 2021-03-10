package seedu.address.model.human.driver;

import java.util.Objects;

import seedu.address.model.human.Name;
import seedu.address.model.human.Person;
import seedu.address.model.human.Phone;
import seedu.address.model.human.person.Human;

/**
 * Represents a Driver to be attached to a Person.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Driver extends Human {

    /**
     * Every field must be present and not null. Guaranteed by parent constructor.
     */
    public Driver(Name name, Phone phone) {
        super(name, phone);
    }

    public Name getName() {
        return super.name;
    }

    public Phone getPhone() {
        return super.phone;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameDriver(Driver otherDriver) {
        if (otherDriver == this) {
            return true;
        }

        return otherDriver != null
                && otherDriver.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone());
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
                .append(getPhone())
                .append("; Email: ");

        return builder.toString();
    }
}
