package seedu.address.storage;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;

/**
 * Utility class to verify JsonAdapted classes.
 */
public class StorageUtil {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "%s's %s field is missing!";
    private final String modelClassname;

    public StorageUtil(String modelClassname) {
        this.modelClassname = modelClassname;
    }

    /**
     * Converts the given {@code List<JsonAdaptedTag>} to a {@code Set<Tag>} by calling
     * {@code JsonAdaptedTag::toModelType}.
     *
     * @param tagList the List of JsonAdaptedTag to convert.
     * @return the Set of {@code Tag} compatible with {@code Model}.
     * @throws IllegalValueException if any of the {@code Tag}s is invalid or empty.
     */
    public Set<Tag> convertAdaptedTagsToModel(List<JsonAdaptedTag> tagList) throws IllegalValueException {
        final Set<Tag> modelTags = new HashSet<>();
        for (JsonAdaptedTag tag : tagList) {
            modelTags.add(tag.toModelType());
        }
        return modelTags;
    }

    /**
     * Converts the given {@code List<JsonAdaptedPassenger>} to a {@code List<Passenger>} by calling
     * {@code JsonAdaptedPassenger::toModelType}.
     *
     * @param passengerList the List of JsonAdaptedPassenger to convert.
     * @return the List of {@code Passenger} compatible with {@code Model}.
     * @throws IllegalValueException if any of the {@code Passenger}s is invalid.
     */
    public List<Passenger> convertAdaptedPassengersToModel(List<JsonAdaptedPassenger> passengerList)
            throws IllegalValueException {
        final List<Passenger> modelPassengers = new ArrayList<>();
        for (JsonAdaptedPassenger tag : passengerList) {
            modelPassengers.add(tag.toModelType());
        }
        return modelPassengers;
    }

    /**
     * Verifies that the given name String is a valid Name, and returns it as a {@code Name} if so.
     *
     * @param name the name to verify.
     * @return the name, as a {@code Name}.
     * @throws IllegalValueException if the given name is invalid or empty.
     */
    public Name verifyAndReturnName(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, modelClassname,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Verifies that the given phone String is a valid Phone, and returns it as a {@code Phone} if so.
     *
     * @param phone the phone String to verify.
     * @return the phone number, as a {@code Phone}.
     * @throws IllegalValueException if the given phone is invalid or empty.
     */
    public Phone verifyAndReturnPhone(String phone) throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, modelClassname,
                    Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    /**
     * Verifies that the given address String is a valid Address, and returns it as a {@code Address} if so.
     *
     * @param address the address String to verify.
     * @return the address, as a {@code Address}.
     * @throws IllegalValueException if the given address is invalid or empty.
     */
    public Address verifyAndReturnAddress(String address) throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, modelClassname,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    /**
     * Verifies that the given tripDay String is a valid TripDay, and returns it as a {@code TripDay} if so.
     *
     * @param tripDay the trip day String to verify.
     * @return the trip day, as a {@code TripDay}.
     * @throws IllegalValueException if the given tripDay is invalid or empty.
     */
    public TripDay verifyAndReturnTripDay(String tripDay) throws IllegalValueException {
        if (tripDay == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, modelClassname,
                    TripDay.class.getSimpleName()));
        }

        final DayOfWeek day;

        try {
            day = DayOfWeek.valueOf(tripDay);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(TripDay.MESSAGE_CONSTRAINTS);
        }

        return new TripDay(day);
    }

    /**
     * Verifies that the given tripTime String is a valid TripTime, and returns it as a {@code TripTime} if so.
     *
     * @param tripTime the trip time String to verify.
     * @return the trip time, as a {@code TripTime}.
     * @throws IllegalValueException if the given tripTime is invalid or empty.
     */
    public TripTime verifyAndReturnTripTime(String tripTime) throws IllegalValueException {
        if (tripTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, modelClassname,
                    TripTime.class.getSimpleName()));
        }
        final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        final LocalTime parsedTimeObject;

        try {
            parsedTimeObject = LocalTime.parse(tripTime, timeFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(TripTime.MESSAGE_CONSTRAINTS);
        }

        return new TripTime(parsedTimeObject);
    }

    /**
     * Verifies that the given price String is a valid Price, and returns it as a {@code Price} if so.
     *
     * @param price the price String to verify.
     * @return the price, as a {@code Price}, if the price is not empty, null otherwise.
     * @throws IllegalValueException if the given price is invalid.
     */
    public Price verifyAndReturnPrice(String price) throws IllegalValueException {
        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, modelClassname,
                    Price.class.getSimpleName()));
        }
        if (price.isEmpty()) {
            return null;
        }

        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }

        return new Price(Double.parseDouble(price));
    }
}
