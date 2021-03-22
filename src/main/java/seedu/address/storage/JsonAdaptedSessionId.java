package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.SessionId;
import seedu.address.model.tag.Tag;

public class JsonAdaptedSessionId {
    private final String sessionId;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedSessionId(SessionId source) {
        sessionId = source.getSessionId();
    }

    @JsonValue
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public SessionId toModelType() throws IllegalValueException {
        if (!SessionId.isValidSessionId(sessionId)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new SessionId(sessionId);
    }
}
