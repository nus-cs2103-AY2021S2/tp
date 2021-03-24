package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private final String name;
    private final String phone;
    private final String address;
    private final String tripDayStr;
    private final String tripTimeStr;
    private final String priceStr;
    private final String driverStr;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPassenger} with the given passenger details.
     */
    @JsonCreator
    public JsonAdaptedPassenger(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("address") String address, @JsonProperty("tripDay") String tripDayStr,
                                @JsonProperty("tripTime") String tripTimeStr, @JsonProperty("price") String priceStr,
                                @JsonProperty("driver") String driverStr,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.tripDayStr = tripDayStr;
        this.tripTimeStr = tripTimeStr;
        this.priceStr = priceStr;
        this.driverStr = driverStr;
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
        tripDayStr = source.getTripDayAsStr();
        tripTimeStr = source.getTripTimeAsStr();
        priceStr = source.getPriceAsStr();
        driverStr = source.getDriverAsStr();
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

        if (tripDayStr == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TripDay.class.getSimpleName()));
        }
        DayOfWeek day;
        try {
            day = DayOfWeek.valueOf(tripDayStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(TripDay.MESSAGE_CONSTRAINTS);
        }
        final TripDay modelTripDay = new TripDay(day);

        if (tripTimeStr == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TripTime.class.getSimpleName()));
        }
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        LocalTime parsedTimeObject;
        try {
            parsedTimeObject = LocalTime.parse(tripTimeStr, timeFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(TripTime.MESSAGE_CONSTRAINTS);
        }
        final TripTime modelTripTime = new TripTime(parsedTimeObject);

        if (driverStr == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Driver.class.getSimpleName()));
        }

        final Set<Tag> modelTags = new HashSet<>(passengerTags);

        if (priceStr == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(priceStr)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        double priceNum = Double.parseDouble(priceStr);
        final Optional<Price> modelPrice = Optional.of(new Price(priceNum));

        if (Driver.isValidDriver(driverStr)) {
            final Driver modelDriver = new Driver(driverStr);
            return new Passenger(modelName, modelPhone, modelAddress, modelTripDay, modelTripTime, modelPrice,
                    modelDriver, modelTags);
        } else {
            return new Passenger(modelName, modelPhone, modelAddress, modelTripDay, modelTripTime, modelPrice,
                    modelTags);
        }
    }

}
