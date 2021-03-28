package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.grade.Grade;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tag.Tag;

public class JsonAdaptedGrade {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Grade's %s field"
            + " is missing!";

    private final String subject;
    private final String gradedItem;
    private final String grade;

    /**
     * Primary Constructor to create Json Adapted Grade
     */
    @JsonCreator
    public JsonAdaptedGrade(@JsonProperty("subject") String subject,
                                  @JsonProperty("gradedItem") String gradedItem,
                                  @JsonProperty("grade") String grade) {
        this.subject = subject;
        this.gradedItem = gradedItem;
        this.grade = grade;
    }

    /**
     * Converts a given {@code Grade} into this class for Jackson use.
     */
    public JsonAdaptedGrade(Grade grade) {
        this.subject = grade.getSubject().name;
        this.gradedItem = grade.getGradedItem();
        this.grade = grade.getGrade();
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code
     * Grade } object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Grade toModelType() throws IllegalValueException {

        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SubjectName.class.getSimpleName()));
        }

        if (!SubjectName.isValidName(subject)) {
            throw new IllegalValueException(SubjectName.MESSAGE_CONSTRAINTS);
        }

        final SubjectName modelSubjectName = new SubjectName(subject);

        if (!Grade.isValidGradedItem(gradedItem)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new Grade(modelSubjectName, gradedItem, grade);

    }
}
