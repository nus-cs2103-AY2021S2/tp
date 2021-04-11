package seedu.address.model.person.passenger;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Passenger in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Passenger extends Person {
    private static final String MESSAGE_NO_PRICE_STATED = "No price was listed by the passenger.";

    // Data fields
    private final Address address;
    private final TripDay tripDay;
    private final TripTime tripTime;
    private final Optional<Price> price;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Passenger(Name name, Phone phone, Address address, TripDay tripDay, TripTime tripTime, Optional<Price> price,
                     Set<Tag> tags) {
        super(name, phone);
        requireAllNonNull(address, tripDay, tripTime, tags);
        this.address = address;
        this.tripDay = tripDay;
        this.tripTime = tripTime;
        this.price = price;
        this.tags.addAll(tags);
    }

    public Address getAddress() {
        return address;
    }

    public TripDay getTripDay() {
        return tripDay;
    }

    public String getTripDayAsStr() {
        return tripDay.toString();
    }

    public TripTime getTripTime() {
        return tripTime;
    }

    public String getTripTimeAsStr() {
        return tripTime.toString();
    }

    public Optional<Price> getPrice() {
        return price;
    }

    public String priceToString() {
        return price.map(Price::toString).orElse(MESSAGE_NO_PRICE_STATED);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both passengers have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePassenger(Passenger otherPassenger) {
        if (otherPassenger == this) {
            return true;
        }

        return otherPassenger != null
                && otherPassenger.getName().equals(getName())
                && otherPassenger.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both passengers have the same identity and data fields.
     * This defines a stronger notion of equality between two passengers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Passenger)) {
            return false;
        }

        Passenger otherPassenger = (Passenger) other;
        return otherPassenger.getName().equals(getName())
                && otherPassenger.getPhone().equals(getPhone())
                && otherPassenger.getAddress().equals(getAddress())
                && otherPassenger.getTripDay().equals(getTripDay())
                && otherPassenger.getTripTime().equals(getTripTime())
                && otherPassenger.getPrice().equals(getPrice())
                && otherPassenger.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, address, tripDay, tripTime, price, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress())
                .append("; Pool Day: ")
                .append(getTripDay())
                .append("; Pool Time: ")
                .append(getTripTime());
        getPrice().ifPresent(
            price -> builder.append("; Price: ").append(price)
        );

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
