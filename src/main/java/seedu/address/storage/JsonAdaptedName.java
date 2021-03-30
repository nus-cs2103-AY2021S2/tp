package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedName {

    private final String personName;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code personName}.
     */
    @JsonCreator
    public JsonAdaptedName(String personName) {
        this.personName = personName;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedName(Name source) {
        personName = source.fullName;
    }

    /**
     * Converts this Jackson-friendly adapted Name object into the model's {@code Name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Name toModelType() throws IllegalValueException {
        if (personName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(personName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(personName);
        return new Name(modelName.fullName);
    }

}
