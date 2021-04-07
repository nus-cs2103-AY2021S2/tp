package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103;
import static seedu.address.storage.JsonAdaptedExam.MESSAGE_CONSTRAINTS;
import static seedu.address.storage.JsonAdaptedExam.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.VALID_EXAM;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

class JsonAdaptedExamTest {
    private static final String INVALID_EXAM_DATE = "35-01-2020 2359";

    @Test
    void toModelType_validExamDetails_returnExam() throws Exception {
        JsonAdaptedExam exam =
            new JsonAdaptedExam(VALID_EXAM);
        assertEquals(VALID_EXAM, exam.toModelType());
    }

    @Test
    void toModelType_nullExamDate_throwsIllegalValueException() {
        JsonAdaptedExam exam =
            new JsonAdaptedExam(null, VALID_TITLE_CS2103);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_invalidLocalDateTime_throwsIllegalValueException() {
        JsonAdaptedExam exam =
            new JsonAdaptedExam(INVALID_EXAM_DATE, VALID_TITLE_CS2103);

        String expectedMessage = String.format(MESSAGE_CONSTRAINTS, LocalDateTime.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedExam exam =
            new JsonAdaptedExam(VALID_EXAM_DATETIME_1, null);
        String expectedMessage = String.format(JsonAdaptedExam.MISSING_FIELD_MESSAGE_FORMAT,
            Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedExam exam =
            new JsonAdaptedExam(VALID_EXAM_DATETIME_1, "@");
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exam::toModelType);
    }

}
