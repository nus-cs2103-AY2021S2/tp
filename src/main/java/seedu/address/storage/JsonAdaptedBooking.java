package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;

/**
 * Jackson-friendly version of {@link Booking}.
 */
class JsonAdaptedBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String tenantName;
    private final String phone;
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("name") String tenantName, @JsonProperty("phone") String phone,
                             @JsonProperty("start") LocalDate start, @JsonProperty("end") LocalDate end) {
        this.tenantName = tenantName;
        this.phone = phone;
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        tenantName = source.getTenantName().toString();
        phone = source.getPhone().toString();
        start = source.getStart();
        end = source.getEnd();
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType() throws IllegalValueException {

        if (tenantName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TenantName.class.getSimpleName()));
        }
        if (!TenantName.isValidName(tenantName)) {
            throw new IllegalValueException(TenantName.MESSAGE_CONSTRAINTS);
        }
        final TenantName modelTenantName = new TenantName(tenantName);

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
        return new Booking(modelTenantName, modelPhone, start, end);
    }

}
