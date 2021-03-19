package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalRemindMe.BENSON;
import static seedu.address.testutil.TypicalRemindMe.VALID_ASSIGNMENT_1;

class JsonAdaptedAssignmentTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_DEADLINE = "35-01-2020 2359";



    @Test
    void toModelType_validAssignmentDetails_returnAssignment() throws Exception{
        JsonAdaptedAssignment assignment =
            new JsonAdaptedAssignment(VALID_ASSIGNMENT_DESCRIPTION_1,
                VALID_EXAM_DATETIME_1, VALID_TITLE_CS2103);

        assertEquals(VALID_ASSIGNMENT_1, assignment.toModelType());
    }
}