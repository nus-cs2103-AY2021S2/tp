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
        if (!Meeting.isValidMeeting(date, time, description)) {
            throw new IllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        return new Meeting(date, time, description);
    }
}
