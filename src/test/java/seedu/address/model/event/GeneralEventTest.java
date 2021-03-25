package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Description;

public class GeneralEventTest {
    private final Description description1 = new Description("Event1");
    private final Description description2 = new Description("Event2");
    private final LocalDateTime date1 = LocalDateTime.of(2021, 3, 23, 23, 59);
    private final LocalDateTime date2 = LocalDateTime.of(2021, 4, 22, 10, 15);
    private final GeneralEvent event1 = new GeneralEvent(description1, date1);
    private final GeneralEvent event2 = new GeneralEvent(description2, date2);

    @Test
    public void setDescription() {
        GeneralEvent copyEvent1 = new GeneralEvent(description1, date1);
        GeneralEvent editedEvent1 = new GeneralEvent(description2, date1);
        assertNotEquals(copyEvent1, editedEvent1);
        assertEquals(copyEvent1.setDescription(description2), editedEvent1);
    }

    @Test
    public void setDate() {
        GeneralEvent copyEvent2 = new GeneralEvent(description2, date2);
        assertEquals(event2, copyEvent2);
        assertNotEquals(event2, copyEvent2.setDate(date1));
    }

    @Test
    public void isSameEvent() {
        // null -> returns false
        assertFalse(event1.isSameEvent(null));

        // same object -> returns true
        assertTrue(event1.isSameEvent(event1));

        // different events -> returns false
        assertFalse(event1.isSameEvent(event2));

        // same attributes -> returns true
        GeneralEvent copyEvent1 = new GeneralEvent(description1, date1);
        assertTrue(event1.isSameEvent(copyEvent1));
    }

    @Test
    public void equals() {
        // null -> returns false
        assertFalse(event1.equals(null));

        // same object -> returns true
        assertTrue(event1.equals(event1));

        // different instance -> return false
        assertFalse(event1.equals(2));

        // same attributes -> return true
        GeneralEvent copyEvent2 = new GeneralEvent(description2, date2);
        assertTrue(event2.equals(copyEvent2));

        // different attributes -> return false
        assertFalse(event1.equals(event2));

        // different description but same date -> return false
        assertFalse(event2.equals(copyEvent2.setDescription(description1)));

        // same description but different date -> return false
        assertFalse(event2.equals(copyEvent2.setDate(date1)));
    }
}
