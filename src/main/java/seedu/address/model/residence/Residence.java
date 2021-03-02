package seedu.address.model.residence;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Residence in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Residence {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final BookingDetails bookingDetails;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Residence(Name name, Address address,BookingDetails bookingDetails, Set<Tag> tags) {
        this.bookingDetails = bookingDetails;
        requireAllNonNull(name, address, tags);
        this.name = name;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public BookingDetails getBookingDetails() {
        return bookingDetails;
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
    public boolean isSameResidence(Residence otherResidence) {
        if (otherResidence == this) {
            return true;
        }

        return otherResidence != null
                && otherResidence.getName().equals(getName());
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

        if (!(other instanceof Residence)) {
            return false;
        }

        Residence otherResidence = (Residence) other;
        return otherResidence.getName().equals(getName())
                && otherResidence.getAddress().equals(getAddress())
                && otherResidence.getBookingDetails().equals(getBookingDetails())
                && otherResidence.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, bookingDetails, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Address: ")
                .append(getAddress())
                .append("; Booking Details: ")
                .append(getBookingDetails());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
