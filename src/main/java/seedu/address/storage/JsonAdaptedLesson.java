package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
public class JsonAdaptedLesson {

    private final String lessonDetails;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given {@code lessonDetails}.
     */
    @JsonCreator
    public JsonAdaptedLesson(String lessonDetails) {
        this.lessonDetails = lessonDetails;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        lessonDetails = source.toString();
    }

    @JsonValue
    public String getLessonDetails() {
        return lessonDetails;
    }

    /**
     * Converts this Jackson-friendly adapted Lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (!Lesson.isValidLesson(Lesson.getDetails(lessonDetails))) {
            throw new IllegalValueException(Lesson.MESSAGE_CONSTRAINTS);
        }
        return new Lesson(lessonDetails);
    }

}
