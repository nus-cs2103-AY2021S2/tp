//@@author
package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Passenger objects.
 */
public class PassengerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final DayOfWeek DEFAULT_TRIPDAY = DayOfWeek.FRIDAY;
    public static final LocalTime DEFAULT_TRIPTIME = LocalTime.of(18, 0);

    private Name name;
    private Phone phone;
    private Address address;
    private TripDay tripDay;
    private TripTime tripTime;
    private Optional<Price> price;
    private Set<Tag> tags;

    /**
     * Creates a {@code PassengerBuilder} with the default details.
     */
    public PassengerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        tripDay = new TripDay(DEFAULT_TRIPDAY);
        tripTime = new TripTime(DEFAULT_TRIPTIME);
        price = Optional.empty();
        tags = new HashSet<>();
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
    //@@author ZechariahTan
    /**
     * Sets the {@code TripDay} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withTripDay(DayOfWeek tripDay) {
        this.tripDay = new TripDay(tripDay);
        return this;
    }

    /**
     * Sets the {@code TripTime} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withTripTime(LocalTime tripTime) {
        this.tripTime = new TripTime(tripTime);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Passenger} that we are building.
     */
    public PassengerBuilder withPrice(Double price) {
        this.price = Optional.of(new Price(price));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Passenger} that we are building to empty.
     */
    public PassengerBuilder withPrice() {
        this.price = Optional.empty();
        return this;
    }

    public Passenger build() {
        return new Passenger(name, phone, address, tripDay, tripTime, price, tags);
    }
}
