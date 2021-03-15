package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.plan.Semester;

/**
 * Jackson-friendly version of {@link Semester}.
 */
class JsonAdaptedSemester {

    private final int semNumber;

    /**
     * Constructs a {@code JsonAdaptedSemester} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedSemester(@JsonProperty("semNumber") int semNumber) {
        this.semNumber = semNumber;
    }

    /**
     * Converts a given {@code Semester} into this class for Jackson use.
     */
    public JsonAdaptedSemester(Semester source) {
        semNumber = source.getSemNumber();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Semester toModelType() throws IllegalValueException {
        return new Semester(semNumber);
    }

}
