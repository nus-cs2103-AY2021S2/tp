package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Pool objects.
 */
public class PoolBuilder {

    public static final DayOfWeek DEFAULT_TRIPDAY = VALID_TRIPDAY_MONDAY;
    public static final LocalTime DEFAULT_TRIPTIME = VALID_TRIPTIME_MORNING;

    private Driver driver;
    private TripDay tripDay;
    private TripTime tripTime;
    private List<Passenger> passengers;
    private Set<Tag> tags;
    private Model model;

    /**
     * Creates a {@code PoolBuilder} with the default details.
     */
    public PoolBuilder() {
        driver = new DriverBuilder().build();
        tripDay = new TripDay(DEFAULT_TRIPDAY);
        tripTime = new TripTime(DEFAULT_TRIPTIME);
        passengers = new ArrayList<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PoolBuilder with the data of {@code poolToCopy}.
     */
    public PoolBuilder(Pool poolToCopy) {
        driver = poolToCopy.getDriver();
        tripDay = poolToCopy.getTripDay();
        tripTime = poolToCopy.getTripTime();
        passengers = new ArrayList<>(poolToCopy.getPassengers());
        tags = new HashSet<>(poolToCopy.getTags());
    }

    /**
     * Sets the passengers of the Pool to a default set of passengers.
     */
    public PoolBuilder withDefaultPassengers() {
        passengers = new PassengerListBuilder().withDefaultPassengers().build();
        return this;
    }

    /**
     * Sets the {@code Driver} of the {@code Pool} that we are building.
     */
    public PoolBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    /**
     * Sets the {@code Model} of the {@code Pool} that we are building.
     */
    public PoolBuilder withModel(Model model) {
        this.model = model;
        return this;
    }

    /**
     * Adds passengers at the given index to the {@code Passengers} of the {@code Pool} that we are building.
     * @param index the {@code Index}s of the {@code Passenger}s to add to the Pool.
     * @return a {@code PoolBuilder} with the {@code Passenger}s set.
     */
    public PoolBuilder withIndex(Index ... index) {
        ObservableList<Passenger> passengerList = model.getFilteredPassengerList();

        for (Index i : index) {
            this.passengers.add(passengerList.get(i.getZeroBased()));
        }

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
    public PoolBuilder withPassengers(List<Passenger> passengers) {
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
