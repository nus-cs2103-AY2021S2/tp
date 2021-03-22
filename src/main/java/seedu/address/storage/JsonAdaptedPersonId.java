package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.PersonId;
import seedu.address.model.tag.Tag;

public class JsonAdaptedPersonId {
    private final String personId;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPersonId(String sessionId) {
        this.personId = sessionId;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPersonId(PersonId source) {
        personId = source.getPersonId();
    }
    public JsonAdaptedPersonId() {
        personId = "";
    }

    @JsonValue
    public String getPersonId() {
        return personId;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PersonId toModelType() throws IllegalValueException {
        if (personId.equals("")) {
            return new PersonId("");
        }
        if (!PersonId.isValidPersonId(personId)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new PersonId(personId);
    }
}
