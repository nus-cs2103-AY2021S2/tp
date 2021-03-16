package seedu.address.storage;

import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.medical.Appointment;

public class JsonAdaptedAppointment {
    private final String dateString;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String dateString) {
        this.dateString = dateString;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        dateString = source.getDateStorage();
    }

    @JsonValue
    public String getDateString() {
        return dateString;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Appointment toModelType() throws IllegalValueException {
        try {
            LocalDateTime date = LocalDateTime.parse(dateString, DATE_FORMAT_STORAGE);
            return new Appointment(date);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new IllegalValueException(Appointment.MESSAGE_CONSTRAINTS_DATE_FORMAT);
        }
    }
}
