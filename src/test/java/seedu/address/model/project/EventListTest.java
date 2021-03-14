package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;

public class EventListTest {

    @Test
    public void constructor_empty_createEmptyEventList() {
        EventList emptyEventList = new EventList();
        assertTrue(emptyEventList.getEvents().isEmpty());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventList(null));
    }

    @Test
    public void constructor_singleEvent_success() {
        Event event = new Event("Test Event", Interval.NONE, LocalDate.of(2020, 1, 1));
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        assertDoesNotThrow(() -> new EventList(events));
    }

    @Test
    public void getEvent_validEvent_equalsOriginalList() {
        Event event = new Event("Test Event", Interval.NONE, LocalDate.of(2020, 1, 1));
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        EventList eventList = new EventList(events);
        assertEquals(events, eventList.getEvents());
    }

    @Test
    public void getCopyOf_validEventList_copyOfOriginal() {
        EventList eventList = new EventList();
        EventList eventListCopy = eventList.getCopy();
        assertEquals(eventList, eventListCopy);
        assertFalse(eventList == eventListCopy);
    }
}
