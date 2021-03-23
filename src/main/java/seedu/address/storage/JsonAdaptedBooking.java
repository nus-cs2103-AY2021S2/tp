package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;

/**
 * Json-friendly version of {@link Booking}.
 */
class JsonAdaptedBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String name;
    private final String phone;
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("start") LocalDate start, @JsonProperty("end") LocalDate end) {
        this.name = name;
        this.phone = phone;
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Booking} into this class for Json use.
     */
    public JsonAdaptedBooking(Booking source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        start = source.getStart();
        end = source.getEnd();
    }

    /**
     * Converts this Json-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType() throws IllegalValueException {

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

        if (start == null || end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Booking.class.getSimpleName()));
        }
        if (start.isAfter(end)) {
            throw new IllegalValueException(Booking.MESSAGE_CONSTRAINTS);
        }
        return new Booking(modelName, modelPhone, start, end);
    }

}
