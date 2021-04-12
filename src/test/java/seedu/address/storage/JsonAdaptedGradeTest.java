package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGrade.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGrades.MATHS_GRADE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.subject.SubjectName;

public class JsonAdaptedGradeTest {

    public static final String INVALID_SUBJECT = "Science&"; // '&' not allowed in subject names
    public static final String INVALID_GRADED_ITEM = "Midterm&"; // '&' not allowed in graded items
    public static final String INVALID_GRADE = "A-"; // '-' not allowed in grade

    private static final String VALID_SUBJECT = MATHS_GRADE.getSubject().toString();
    private static final String VALID_GRADED_ITEM = MATHS_GRADE.getGradedItem().toString();
    private static final String VALID_GRADE = MATHS_GRADE.getGrade().toString();

    @Test
    public void toModelType_validGradeDetails_returnsGrade() throws Exception {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(MATHS_GRADE);
        assertEquals(MATHS_GRADE, grade.toModelType());
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
                new JsonAdaptedGrade(INVALID_SUBJECT, VALID_GRADED_ITEM, VALID_GRADE);
        String expectedMessage = Grade.SUBJECT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
                new JsonAdaptedGrade(null, VALID_GRADED_ITEM, VALID_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SubjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidGradedItem_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
                new JsonAdaptedGrade(VALID_SUBJECT, INVALID_GRADED_ITEM, VALID_GRADE);
        String expectedMessage = GradedItem.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_nullGradedItem_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
                new JsonAdaptedGrade(VALID_SUBJECT, null, VALID_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GradedItem.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
                new JsonAdaptedGrade(VALID_SUBJECT, VALID_GRADED_ITEM, INVALID_GRADE);
        String expectedMessage = GradeEnum.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedGrade grade =
                new JsonAdaptedGrade(VALID_SUBJECT, VALID_GRADED_ITEM, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GradeEnum.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }
}
