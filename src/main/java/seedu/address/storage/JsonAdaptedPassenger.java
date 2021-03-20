package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Passenger}.
 */
class JsonAdaptedPassenger {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Passenger's %s field is missing!";
    //todo Remove STUB_VALID_PRICE declaration
    private static final Optional<Price> STUB_VALID_PRICE = Optional.of(new Price("1.69"));

    private final String name;
    private final String phone;
    private final String address;
    private final String tripDay;
    private final String tripTime;
    private final String driver;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPassenger} with the given passenger details.
     */
    @JsonCreator
    public JsonAdaptedPassenger(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("address") String address, @JsonProperty("tripDay") String tripDay,
                             @JsonProperty("tripTime") String tripTime, @JsonProperty("driver") String driver,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.tripDay = tripDay;
        this.tripTime = tripTime;
        this.driver = driver;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Passenger} into this class for Jackson use.
     */
    public JsonAdaptedPassenger(Passenger source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        tripDay = source.getTripDay().value;
        tripTime = source.getTripTime().value;
        driver = source.getDriverStr();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted passenger object into the model's {@code Passenger} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted passenger.
     */
    public Passenger toModelType() throws IllegalValueException {
        final List<Tag> passengerTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            passengerTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (tripDay == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TripDay.class.getSimpleName()));
        }
        if (!TripDay.isValidTripDay(tripDay)) {
            throw new IllegalValueException(TripDay.MESSAGE_CONSTRAINTS);
        }
        final TripDay modelTripDay = new TripDay(tripDay);

        if (tripTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TripTime.class.getSimpleName()));
        }
        if (!TripTime.isValidTripTime(tripTime)) {
            throw new IllegalValueException(TripTime.MESSAGE_CONSTRAINTS);
        }

        if (driver == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Driver.class.getSimpleName()));
        }

        final TripTime modelTripTime = new TripTime(tripTime);
        final Set<Tag> modelTags = new HashSet<>(passengerTags);

        //todo remove STUB_VALID_PRICE usage
        final Optional<Price> modelPrice = STUB_VALID_PRICE;

        if (Driver.isValidDriver(driver)) {
            final Driver modelDriver = new Driver(driver);
            return new Passenger(modelName, modelPhone, modelAddress, modelTripDay, modelTripTime, modelPrice,
                    modelDriver, modelTags);
        } else {
            return new Passenger(modelName, modelPhone, modelAddress, modelTripDay, modelTripTime, modelPrice,
                    modelTags);
        }
    }

}
