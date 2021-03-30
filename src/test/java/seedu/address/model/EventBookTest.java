package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_CS2107;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CS2030;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.testutil.EventBuilder;

public class EventBookTest {
    private final EventBook eventBook = new EventBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventBook.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEventBook_replacesData() {
        EventBook newData = new EventBook();
        eventBook.resetData(newData);
        assertEquals(newData, eventBook);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two persons with the same identity fields
        Event editedCS2030 = new EventBuilder(CS2030).withDescription(VALID_DESCRIPTION_CS2107)
                .withStatus(VALID_STATUS_CS2107).build();
        List<Event> newEvents = Arrays.asList(CS2030, editedCS2030);
        EventBookStub newData = new EventBookStub(newEvents);

        assertThrows(DuplicateEventException.class, () -> eventBook.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInEventBook_returnsFalse() {
        assertFalse(eventBook.hasEvent(CS2030));
    }

    @Test
    public void hasEvent_eventInEventBook_returnsTrue() {
        eventBook.addEvent(CS2030);
        assertTrue(eventBook.hasEvent(CS2030));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInEventBook_returnsTrue() {
        eventBook.addEvent(CS2030);
        Event editedCS2030 = new EventBuilder(CS2030).withDescription(VALID_DESCRIPTION_CS2107)
                .withStatus(VALID_STATUS_CS2107).build();
        assertTrue(eventBook.hasEvent(editedCS2030));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eventBook.getEventList().remove(0));
    }

    /**
     * A stub ReadOnlyEventBook whose persons list can violate interface constraints.
     */
    private static class EventBookStub implements ReadOnlyEventBook {
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        EventBookStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

}
