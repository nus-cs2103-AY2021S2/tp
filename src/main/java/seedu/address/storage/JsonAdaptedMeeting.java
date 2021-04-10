package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    private final String meeting;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code meeting}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(String meeting) {
        this.meeting = meeting;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        meeting = source.meeting;
    }

    @JsonValue
    public String getMeeting() {
        return meeting;
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (!Meeting.isValidMeeting(meeting)) {
            throw new IllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        return Meeting.newMeeting(meeting);
    }

}
