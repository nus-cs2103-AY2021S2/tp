package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DESCRIPTION_1;
import static seedu.address.storage.JsonAdaptedGeneralEvent.MESSAGE_CONSTRAINTS;
import static seedu.address.storage.JsonAdaptedGeneralEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.VALID_GENERAL_EVENT_1;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Description;

class JsonAdaptedGeneralEventTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_DATE = "35-01-2020 2359";

    @Test
    void toModelType_validGeneralEventDetails_returnGeneralEvent() throws Exception {
        JsonAdaptedGeneralEvent generalEvent =
            new JsonAdaptedGeneralEvent(VALID_GENERAL_EVENT_1);
        assertEquals(VALID_GENERAL_EVENT_1, generalEvent.toModelType());
    }

    @Test
    void toModelType_invalidGeneralEventDetails_throwsIllegalValueException() {
        JsonAdaptedGeneralEvent generalEvent =
            new JsonAdaptedGeneralEvent(INVALID_DESCRIPTION, VALID_GENERAL_EVENT_DATE_1);
        String expectedMessage = String.format(Description.MESSAGE_CONSTRAINTS, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, generalEvent::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedGeneralEvent generalEvent =
            new JsonAdaptedGeneralEvent(null, VALID_GENERAL_EVENT_DATE_1);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, generalEvent::toModelType);
    }

    @Test
    public void toModelType_invalidLocalDateTime_throwsIllegalValueException() {
        JsonAdaptedGeneralEvent generalEvent =
            new JsonAdaptedGeneralEvent(VALID_GENERAL_EVENT_DESCRIPTION_1, INVALID_DATE);

        String expectedMessage = String.format(MESSAGE_CONSTRAINTS, LocalDateTime.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, generalEvent::toModelType);
    }
}
