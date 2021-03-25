package seedu.address.model.trip;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.tag.Tag;

/**
 * Represents a Trip.
 * Guarantees: details relevant for a trip are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Data fields
    private final TripDay tripDay;
    private final TripTime tripTime;
    private final Set<Passenger> passengers;
    private final Set<Tag> tags = new HashSet<>();
    private Driver driver;

    /**
     * Every field must be present and not null.
     */
    public Trip(Driver driver, TripDay tripDay, TripTime tripTime, Set<Passenger> passengers, Set<Tag> tags) {
        requireAllNonNull(driver, tripDay, tripTime);

        // ensures a trip cannot be created with no passengers
        if (passengers.isEmpty()) {
            throw new NullPointerException();
        }

        this.tripDay = tripDay;
        this.tripTime = tripTime;
        this.driver = driver;
        this.passengers = new HashSet<>(passengers);
        this.tags.addAll(tags);
    }

    public String getTripDayAsStr() {
        return tripDay.toString();
    }

    public TripDay getTripDay() {
        return tripDay;
    }

    public String getTripTimeAsStr() {
        return tripTime.toString();
    }

    public TripTime getTripTime() {
        return tripTime;
    }

    public String getDriverAsStr() {
        return driver.toString();
    }

    public Driver getDriver() {
        return driver;
    }

    /**
     * Returns an immutable passengers set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Passenger> getPassengers() {
        return Collections.unmodifiableSet(passengers);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both trips have same driver, date & time, and passengers.
     * This defines a weaker notion of equality between two trips.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getDriver().equals(getDriver())
                && otherTrip.getTripDay().equals(getTripDay())
                && otherTrip.getTripTime().equals(getTripTime())
                && otherTrip.getPassengers().equals(getPassengers());
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

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getDriver().equals(getDriver())
                && otherTrip.getTripDay().equals(getTripDay())
                && otherTrip.getTripTime().equals(getTripTime())
                && otherTrip.getPassengers().equals(getPassengers())
                && otherTrip.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(driver, tripDay, tripTime, passengers, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Driver: ")
                .append(getDriverAsStr())
                .append("; Trip Day: ")
                .append(getTripDayAsStr())
                .append("; Trip Time: ")
                .append(getTripTimeAsStr())
                .append("; Passengers: ");

        passengers.forEach(builder::append);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

