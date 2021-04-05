package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "A meeting's %s field is missing!";

    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code date}, {@code time} and {@code description}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("date") LocalDate date, @JsonProperty("time") LocalTime time,
            @JsonProperty("description") String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        date = source.getDate();
        time = source.getTime();
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Meeting} object.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "time"));
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        if (!Meeting.isValidMeeting(date, time, description)) {
            throw new IllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        return new Meeting(date, time, description);
    }
}
