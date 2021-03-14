package seedu.address.storage;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
=======
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
>>>>>>> 3d49dd485089143070617a7d9f2fcd597e1d9183

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
<<<<<<< HEAD
//        if (!Semester.isValidSemester(semNumber)) {
//            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
//        }
=======
        //        if (!Semester.isValidSemester(semNumber)) {
        //            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        //        }
>>>>>>> 3d49dd485089143070617a7d9f2fcd597e1d9183
        return new Semester(semNumber);
    }

}
