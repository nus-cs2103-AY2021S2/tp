package seedu.address.model.human.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.human.Human;
import seedu.address.model.human.Name;
import seedu.address.model.human.Phone;
import seedu.address.model.human.driver.Driver;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person extends Human {

    public static final String MESSAGE_NO_ASSIGNED_DRIVER = "No driver assigned to this passenger.";

    // Data fields
    private final Address address;
    private final TripDay tripDay;
    private final TripTime tripTime;
    private final Set<Tag> tags = new HashSet<>();
    private Optional<Driver> driver;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Address address, TripDay tripDay, TripTime tripTime, Set<Tag> tags) {
        super(name, phone);
        requireAllNonNull(address, tripDay, tripTime, tags);
        this.address = address;
        this.tripDay = tripDay;
        this.tripTime = tripTime;
        this.tags.addAll(tags);
    }

    public Address getAddress() {
        return address;
    }

    public TripDay getTripDay() {
        return tripDay;
    }

    public TripTime getTripTime() {
        return tripTime;
    }

    public String getDriverStr() {
        return driver.isEmpty() ? MESSAGE_NO_ASSIGNED_DRIVER : driver.toString();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTripDay().equals(getTripDay())
                && otherPerson.getTripTime().equals(getTripTime())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, address, tripDay, tripTime, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress())
                .append("; Trip Days: ")
                .append(getTripDay())
                .append("; Trip Times: ")
                .append(getTripTime())
                .append("; Driver: ")
                .append(getDriverStr());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
