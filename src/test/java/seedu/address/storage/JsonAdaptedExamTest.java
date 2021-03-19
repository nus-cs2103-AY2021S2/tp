package seedu.address.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103;
import static seedu.address.storage.JsonAdaptedExam.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.VALID_EXAM;

import java.time.LocalDateTime;
import seedu.address.commons.exceptions.IllegalValueException;

class JsonAdaptedExamTest {
    private static final String INVALID_EXAM_DATE = "35-01-2020 2359";

    @Test
    void toModelType_validExamDetails_returnExam() throws Exception{
        JsonAdaptedExam exam =
            new JsonAdaptedExam(VALID_EXAM_DATETIME_1, VALID_TITLE_CS2103);
        assertEquals(VALID_EXAM, exam.toModelType());
    }

    @Test
    public void toModelType_invalidLocalDateTime_throwsIllegalValueException() {
        JsonAdaptedExam exam =
            new JsonAdaptedExam(INVALID_EXAM_DATE, VALID_TITLE_CS2103);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }
}