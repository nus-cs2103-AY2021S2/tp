package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.repeatable.Event;
import seedu.address.testutil.EventBuilder;

public class JsonAdaptedEventTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_DATE_STRING = "2020-02-02";
    private static final String INVALID_TIME_STRING = "17-30";
    private static final Boolean INVALID_IS_WEEKLY = null;

    private static final String VALID_DESCRIPTION = EventBuilder.DEFAULT_DESCRIPTION;
    private static final LocalDate VALID_DATE = EventBuilder.DEFAULT_DATE;
    private static final LocalTime VALID_TIME = EventBuilder.DEFAULT_TIME;
    private static final Boolean VALID_IS_WEEKLY = EventBuilder.DEFAULT_IS_WEEKLY;
    private static final String VALID_DATE_STRING = "02-02-2020";
    private static final String VALID_TIME_STRING = "17:30";

    private static final Event TUTORIAL = new EventBuilder()
            .withDescription(VALID_DESCRIPTION).withDate(VALID_DATE).withTime(VALID_TIME).withIsWeekly(VALID_IS_WEEKLY)
            .build();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(TUTORIAL);
        assertEquals(TUTORIAL, event.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalArgumentException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_DESCRIPTION, VALID_DATE_STRING, VALID_TIME_STRING, VALID_IS_WEEKLY);
        String expectedMessage = Event.MESSAGE_CONSTRAINTS_DESCRIPTION;
        assertThrows(IllegalArgumentException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_DESCRIPTION, INVALID_DATE_STRING, VALID_TIME_STRING, VALID_IS_WEEKLY);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_DATE_STRING, INVALID_TIME_STRING, VALID_IS_WEEKLY);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_invalidIsWeekly_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_DATE_STRING, VALID_TIME_STRING, INVALID_IS_WEEKLY);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_DATE_STRING, VALID_TIME_STRING, VALID_IS_WEEKLY);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_nullIsWeekly_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_DATE_STRING, VALID_TIME_STRING, null);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

}
