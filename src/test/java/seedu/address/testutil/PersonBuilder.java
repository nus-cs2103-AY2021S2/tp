package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Passenger objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TRIPDAY = "FRIDAY";
    public static final String DEFAULT_TRIPTIME = "1800";

    private Name name;
    private Phone phone;
    private Address address;
    private TripDay tripDay;
    private TripTime tripTime;
    private Set<Tag> tags;
    private Optional<Driver> driver;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        tripDay = new TripDay(DEFAULT_TRIPDAY);
        tripTime = new TripTime(DEFAULT_TRIPTIME);
        tags = new HashSet<>();
        driver = Optional.empty();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Passenger personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        address = personToCopy.getAddress();
        tripDay = personToCopy.getTripDay();
        tripTime = personToCopy.getTripTime();
        tags = new HashSet<>(personToCopy.getTags());
        driver = personToCopy.getDriver();
    }

    /**
     * Sets the {@code Name} of the {@code Passenger} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Passenger} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Passenger} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Passenger} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code TripDay} of the {@code Passenger} that we are building.
     */
    public PersonBuilder withTripDay(String tripDay) {
        this.tripDay = new TripDay(tripDay);
        return this;
    }

    /**
     * Sets the {@code TripTime} of the {@code Passenger} that we are building.
     */
    public PersonBuilder withTripTime(String tripTime) {
        this.tripTime = new TripTime(tripTime);
        return this;
    }

    /**
     * Sets the {@code Driver} of the {@code Passenger} that we are building.
     */
    public PersonBuilder withDriver(Driver driver) {
        this.driver = Optional.ofNullable(driver);
        return this;
    }

    public Passenger build() {
        return new Passenger(name, phone, address, tripDay, tripTime, tags);
    }

    public Passenger buildWithDriver() {
        return new Passenger(name, phone, address, tripDay, tripTime, driver.get(), tags);
    }

}
