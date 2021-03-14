package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;
import seedu.address.testutil.EventBuilder;

public class JsonAdaptedEventTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_DATE_STRING = "2020-02-02";

    private static final String VALID_DESCRIPTION = EventBuilder.DEFAULT_DESCRIPTION;
    private static final Interval VALID_INTERVAL = EventBuilder.DEFAULT_INTERVAL;
    private static final LocalDate VALID_DATE = EventBuilder.DEFAULT_DATE;
    private static final String VALID_DATE_STRING = "02-02-2020";
    private static final Boolean VALID_IS_DONE = EventBuilder.DEFAULT_IS_DONE;

    private static final Event TUTORIAL = new EventBuilder()
            .withDescription(VALID_DESCRIPTION).withInterval(VALID_INTERVAL)
            .withAtDate(VALID_DATE).withIsDone(VALID_IS_DONE).build();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(TUTORIAL);
        assertEquals(TUTORIAL, event.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalArgumentException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_DESCRIPTION, VALID_INTERVAL.toString(), VALID_DATE_STRING, VALID_IS_DONE);
        String expectedMessage = Event.MESSAGE_CONSTRAINTS_DESCRIPTION;
        assertThrows(IllegalArgumentException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_INTERVAL.toString(), INVALID_DATE_STRING, VALID_IS_DONE);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsNullPointerException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_INTERVAL.toString(),
                VALID_DATE_STRING, VALID_IS_DONE);
        assertThrows(NullPointerException.class, event::toModelType);
    }
}
