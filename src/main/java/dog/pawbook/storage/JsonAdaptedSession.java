package dog.pawbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.program.Session;

/**
 * Jackson-friendly version of {@link Session}.
 */
class JsonAdaptedSession {

    private final String session;

    /**
     * Constructs a {@code JsonAdaptedSession} with the given {@code session}.
     */
    @JsonCreator
    public JsonAdaptedSession(String session) {
        this.session = session;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        session = source.value;
    }

    @JsonValue
    public String getSession() {
        return session;
    }

    /**
     * Converts this Jackson-friendly adapted session object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {
        if (!Session.isValidDate(session)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(session);
    }

}
