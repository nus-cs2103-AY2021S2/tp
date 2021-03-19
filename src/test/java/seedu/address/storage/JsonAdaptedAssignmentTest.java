package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Description;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103;
import static seedu.address.storage.JsonAdaptedAssignment.MESSAGE_CONSTRAINTS;
import static seedu.address.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.VALID_ASSIGNMENT;

class JsonAdaptedAssignmentTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_DEADLINE = "35-01-2020 2359";



    @Test
    void toModelType_validAssignmentDetails_returnAssignment() throws Exception{
        JsonAdaptedAssignment assignment =
            new JsonAdaptedAssignment(VALID_ASSIGNMENT_DESCRIPTION_1,
                VALID_EXAM_DATETIME_1, VALID_TITLE_CS2103);

        assertEquals(VALID_ASSIGNMENT, assignment.toModelType());
    }

    @Test
    void toModelType_invalidAssignmentDetails_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
            new JsonAdaptedAssignment(INVALID_DESCRIPTION, VALID_EXAM_DATETIME_1, VALID_TITLE_CS2103);
        String expectedMessage = String.format(Description.MESSAGE_CONSTRAINTS, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
            new JsonAdaptedAssignment(null, VALID_EXAM_DATETIME_1, VALID_TITLE_CS2103);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidLocalDateTime_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment =
            new JsonAdaptedAssignment(VALID_ASSIGNMENT_DESCRIPTION_1, INVALID_DEADLINE, VALID_TITLE_CS2103);

        String expectedMessage = String.format(MESSAGE_CONSTRAINTS, LocalDateTime.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }
}