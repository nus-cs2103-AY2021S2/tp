package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_DESERIALIZE_ERROR_DUMP_DATA;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "A meeting's %s field is missing!";

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedMeeting.class);

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

    private IllegalValueException internalIllegalValueException(String message) {
        logger.warning(String.format(MESSAGE_DESERIALIZE_ERROR_DUMP_DATA, "Meeting"));
        logger.warning(this.toString());
        return new IllegalValueException(message);
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Meeting} object.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (date == null) {
            throw internalIllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }

        if (time == null) {
            throw internalIllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "time"));
        }

        if (description == null) {
            throw internalIllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        String trimmedDescription = description.trim();
        if (!Meeting.isValidMeeting(date, time, trimmedDescription)) {
            throw internalIllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        return new Meeting(date, time, trimmedDescription);
    }

    @Override
    public String toString() {
        return "JsonAdaptedMeeting{"
                + "date=" + date
                + ", time=" + time
                + ", description='" + description + '\''
                + "}";
    }
}
