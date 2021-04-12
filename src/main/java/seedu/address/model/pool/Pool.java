package seedu.address.model.pool;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.tag.Tag;

/**
 * Represents a Pool.
 * Guarantees: details relevant for a trip are present and not null, field values are validated, immutable.
 */
public class Pool {

    // Data fields
    private final Driver driver;
    private final TripDay tripDay;
    private final TripTime tripTime;
    private final List<Passenger> passengers;
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Every field must be present and not null. Ensures a pool cannot be created with no passengers.
     */
    public Pool(Driver driver, TripDay tripDay, TripTime tripTime, List<Passenger> passengers, Set<Tag> tags) {
        requireAllNonNull(driver, tripDay, tripTime);

        // ensures a trip cannot be created with no passengers
        if (passengers.isEmpty()) {
            throw new NullPointerException();
        }

        this.tripDay = tripDay;
        this.tripTime = tripTime;
        this.driver = driver;
        this.passengers = new ArrayList<>(passengers);
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
     * Returns an immutable passengers list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    /**
     * Replaces the passenger {@code target} in the internal passenger list with {@code editedPassenger},
     * if {@code target} exists.
     */
    public Pool setPassenger(Passenger target, Passenger editedPassenger) {
        requireAllNonNull(target, editedPassenger);

        int index = passengers.indexOf(target);
        if (index == -1) {
            return this;
        }

        List<Passenger> newPassengers = new ArrayList<>(passengers);
        newPassengers.set(index, editedPassenger);
        return new Pool(driver, tripDay, tripTime, newPassengers, tags);
    }

    public boolean hasPassenger(Passenger key) {
        return passengers.stream().anyMatch(key::equals);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both trips have same driver, date, and time.
     * This defines a weaker notion of equality between two trips.
     */
    public boolean isSamePool(Pool otherPool) {
        if (otherPool == this) {
            return true;
        }

        return otherPool != null
                && otherPool.getDriver().equals(getDriver())
                && otherPool.getTripDay().equals(getTripDay())
                && otherPool.getTripTime().equals(getTripTime());
    }

    /**
     * Presents Passenger names in a single string
     * @return String of Passenger names
     */
    public String getPassengerNames() {
        StringJoiner passengerNames = new StringJoiner(", ");

        for (Passenger p : passengers) {
            passengerNames.add(p.getName().toString());
        }

        return passengerNames.toString();
    }

    /**
     * Returns true if both pools have the same identity and data fields.
     * This defines a stronger notion of equality between two pools.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pool)) {
            return false;
        }

        Pool otherPool = (Pool) other;
        return isSamePool(otherPool)
                && otherPool.getTags().equals(getTags());
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
                .append("; Pool Day: ")
                .append(getTripDayAsStr())
                .append("; Pool Time: ")
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

