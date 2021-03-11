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

    private final String MESSAGE_NO_ASSIGNED_DRIVER = "No driver assigned to this passenger.";

    // Data fields
    private final Address address;
    private final TripDays tripDays;
    private final TripTimes tripTimes;
    private final Set<Tag> tags = new HashSet<>();
    private Optional<Driver> driver;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Address address, TripDays tripDays, TripTimes tripTimes, Set<Tag> tags) {
        super(name, phone);
        requireAllNonNull(address, tripDays, tripTimes, tags);
        this.address = address;
        this.tripDays = tripDays;
        this.tripTimes = tripTimes;
        this.tags.addAll(tags);
    }

    public Address getAddress() {
        return address;
    }

    public TripDays getTripDays() {
        return tripDays;
    }

    public TripTimes getTripTimes() {
        return tripTimes;
    }

    public String getDriverStr() {return driver.isEmpty() ? MESSAGE_NO_ASSIGNED_DRIVER : driver.toString();}

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
                && otherPerson.getTripDays().equals(getTripDays())
                && otherPerson.getTripTimes().equals(getTripTimes())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, address, tripDays, tripTimes, tags);
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
                .append(getTripDays())
                .append("; Trip Times: ")
                .append(getTripTimes())
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
