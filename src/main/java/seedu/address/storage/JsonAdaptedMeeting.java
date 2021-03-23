package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedMeeting {

    private final String meeting;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(String meeting) {
        this.meeting = meeting;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        meeting = source.meeting;
    }

    @JsonValue
    public String getMeeting() {
        return meeting;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (!Meeting.isValidMeeting(meeting)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return Meeting.newMeeting(meeting);
    }

}
