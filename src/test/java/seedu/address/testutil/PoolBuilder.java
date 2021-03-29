package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.TripDay;
import seedu.address.model.pool.TripTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Pool objects.
 */
public class PoolBuilder {

    public static final String DEFAULT_DRIVER_NAME_STR = "Dr Iver Man";
    public static final String DEFAULT_DRIVER_PHONE_STR = "85355255";

    public static final DayOfWeek DEFAULT_TRIPDAY = DayOfWeek.FRIDAY;
    public static final LocalTime DEFAULT_TRIPTIME = LocalTime.of(18, 0);

    private Driver driver;
    private TripDay tripDay;
    private TripTime tripTime;
    private Set<Passenger> passengers;
    private Set<Tag> tags;

    /**
     * Creates a {@code PoolBuilder} with the default details.
     */
    public PoolBuilder() {
        driver = new Driver(new Name(DEFAULT_DRIVER_NAME_STR), new Phone(DEFAULT_DRIVER_PHONE_STR));
        tripDay = new TripDay(DEFAULT_TRIPDAY);
        tripTime = new TripTime(DEFAULT_TRIPTIME);
        passengers = new PassengerSetBuilder().withDefaultPassengers().build();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PoolBuilder with the data of {@code poolToCopy}.
     */
    public PoolBuilder(Pool poolToCopy) {
        driver = poolToCopy.getDriver();
        tripDay = poolToCopy.getTripDay();
        tripTime = poolToCopy.getTripTime();
        passengers = new HashSet<>(poolToCopy.getPassengers());
        tags = new HashSet<>(poolToCopy.getTags());
    }

    /**
     * Sets the {@code Driver} of the {@code Pool} that we are building.
     */
    public PoolBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    /**
     * Sets the {@code Driver} of the {@code Pool} that we are building. Makes driver from String inputs
     */
    public PoolBuilder withDriverFromStr(String driverNameStr, String driverPhoneStr) {
        Name driverName = new Name(driverNameStr);
        Phone driverPhone = new Phone(driverPhoneStr);
        this.driver = new Driver(driverName, driverPhone);
        return this;
    }

    /**
     * Sets the {@code TripDay} of the {@code Pool} that we are building.
     */
    public PoolBuilder withTripDay(DayOfWeek tripDay) {
        this.tripDay = new TripDay(tripDay);
        return this;
    }

    /**
     * Sets the {@code TripTime} of the {@code Pool} that we are building.
     */
    public PoolBuilder withTripTime(LocalTime tripTime) {
        this.tripTime = new TripTime(tripTime);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Pool} that we are building.
     */
    public PoolBuilder withPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Pool} that we are building.
     */
    public PoolBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public Pool build() {
        return new Pool(driver, tripDay, tripTime, passengers, tags);
    }

}
