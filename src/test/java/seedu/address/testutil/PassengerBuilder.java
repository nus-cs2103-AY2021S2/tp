package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Passenger objects.
 */
public class PassengerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TRIPDAY = "FRIDAY";
    public static final String DEFAULT_TRIPTIME = "1800";
    public static final String DEFAULT_PRICE = "1.69";

    private Name name;
    private Phone phone;
    private Address address;
    private TripDay tripDay;
    private TripTime tripTime;
    private Price price;
    private Set<Tag> tags;
    private Optional<Driver> driver;

    /**
     * Creates a {@code PassengerBuilder} with the default details.
     */
    public PassengerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        tripDay = new TripDay(DEFAULT_TRIPDAY);
        tripTime = new TripTime(DEFAULT_TRIPTIME);
        price = new Price(DEFAULT_PRICE);
        tags = new HashSet<>();
        driver = Optional.empty();
    }

    /**
     * Initializes the PassengerBuilder with the data of {@code passengerToCopy}.
     */
    public PassengerBuilder(Passenger passengerToCopy) {
        name = passengerToCopy.getName();
        phone = passengerToCopy.getPhone();
        address = passengerToCopy.getAddress();
        tripDay = passengerToCopy.getTripDay();
        tripTime = passengerToCopy.getTripTime();
        price = passengerToCopy.getPrice();
        tags = new HashSet<>(passengerToCopy.getTags());
        driver = passengerToCopy.getDriver();
    }

    /**
     * Sets the {@code Name} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Passenger} that we are building.
     */
    public PassengerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code TripDay} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withTripDay(String tripDay) {
        this.tripDay = new TripDay(tripDay);
        return this;
    }

    /**
     * Sets the {@code TripTime} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withTripTime(String tripTime) {
        this.tripTime = new TripTime(tripTime);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code Driver} of the {@code Passenger} that we are building, with {@code DriverBuilder}.
     */
    public PassengerBuilder withDriver() {
        this.driver = Optional.of(new DriverBuilder().build());
        return this;
    }

    /**
     * Sets the {@code Driver} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withDriver(Driver driver) {
        this.driver = Optional.ofNullable(driver);
        return this;
    }

    public Passenger build() {
        return new Passenger(name, phone, address, tripDay, tripTime, price, tags);
    }

    public Passenger buildWithDriver() {
        return new Passenger(name, phone, address, tripDay, tripTime, price, driver.get(), tags);
    }

}
