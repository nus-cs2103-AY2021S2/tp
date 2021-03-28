package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateGeneralEventException;
import seedu.address.model.event.exceptions.GeneralEventNotFoundException;
import seedu.address.model.module.Description;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

public class UniqueGeneralEventListTest {
    private final Description description1 = new Description("Event1");
    private final Description description2 = new Description("Event2");
    private final LocalDateTime date1 = LocalDateTime.of(2021, 3, 23, 23, 59);
    private final LocalDateTime date2 = LocalDateTime.of(2021, 4, 22, 10, 15);
    private final GeneralEvent event1 = new GeneralEvent(description1, date1);
    private final GeneralEvent event2 = new GeneralEvent(description2, date2);
    private final GeneralEvent event3 = new GeneralEvent(description1, date2);
    private final GeneralEvent event4 = new GeneralEvent(description2, date1);

    @Test
    public void add() {
        UniqueGeneralEventList list = new UniqueGeneralEventList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list.add(null));

        // add an event
        list.add(event1);
        assertEquals(list.size(), 1);

        // add another event
        list.add(event4);
        assertEquals(list.size(), 2);

        // add duplicate event -> throws
        assertThrows(DuplicateGeneralEventException.class, () -> list.add(event4));
    }

    @Test
    public void contains() {
        UniqueGeneralEventList list = new UniqueGeneralEventList();

        // empty list -> return false
        assertFalse(list.contains(event1));

        // list contains the event -> returns true
        list.add(event1);
        list.add(event2);
        assertTrue(list.contains(event1));

        // list does not contain the event -> returns false
        assertFalse(list.contains(event3));

        // null -> throws
        assertThrows(NullPointerException.class, () -> list.contains(null));
    }

    @Test
    public void getGeneralEvent() {
        UniqueGeneralEventList list = new UniqueGeneralEventList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list.getGeneralEvent(null));

        // event does not exist with empty list -> throws
        assertThrows(GeneralEventNotFoundException.class, () -> list.getGeneralEvent(event1));

        list.add(event1);
        list.add(event2);
        list.add(event3);

        // event does not exist and list is not empty -> throws
        assertThrows(GeneralEventNotFoundException.class, () -> list.getGeneralEvent(event4));

        // event exists -> returns true
        assertEquals(event1, list.getGeneralEvent(event1));
        assertEquals(event3, list.getGeneralEvent(event3));

        // Using Index ---------------------------------------------------------------------------
        UniqueGeneralEventList list2 = new UniqueGeneralEventList();

        // empty list -> throws
        assertThrows(GeneralEventNotFoundException.class, () -> list2.getGeneralEvent(0));

        list2.add(event4);
        list2.add(event2);
        list2.add(event3);

        // event at index 0 -> returns event4
        assertEquals(event4, list2.getGeneralEvent(1));

        // last event -> returns event3
        assertEquals(event3, list2.getGeneralEvent(list2.size()));

        // index < 1 > -> throws
        assertThrows(GeneralEventNotFoundException.class, () -> list2.getGeneralEvent(0));

        // index > size -> throws
        assertThrows(GeneralEventNotFoundException.class, () -> list2.getGeneralEvent(
                list2.size() + 1));
    }

    @Test
    public void setGeneralEvent() {
        UniqueGeneralEventList list1 = new UniqueGeneralEventList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list1.setGeneralEvent(null, null));
        assertThrows(NullPointerException.class, () -> list1.setGeneralEvent(null, event1));
        assertThrows(NullPointerException.class, () -> list1.setGeneralEvent(event1, null));

        // event1 to event4 -> returns true
        list1.add(event1);
        list1.add(event2);
        list1.add(event3);

        UniqueGeneralEventList list2 = new UniqueGeneralEventList();
        list2.add(event4);
        list2.add(event2);
        list2.add(event3);
        list2.setGeneralEvent(event4, event1);

        assertEquals(list1, list2);

        // duplicate event -> throws
        assertThrows(DuplicateGeneralEventException.class, () -> list1.setGeneralEvent(event1,
                event2));
    }

    @Test
    public void remove() {
        UniqueGeneralEventList list1 = new UniqueGeneralEventList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list1.remove(null));

        list1.add(event1);
        list1.add(event2);
        list1.add(event3);

        // remove event not found -> throws
        assertThrows(ModuleNotFoundException.class, () -> list1.remove(event4));

        UniqueGeneralEventList list2 = new UniqueGeneralEventList();
        list2.add(event2);
        list2.add(event3);
        list1.remove(event1);
        assertEquals(list1, list2);
    }

    @Test
    public void setGeneralEvents() {
        UniqueGeneralEventList list1 = new UniqueGeneralEventList();
        UniqueGeneralEventList list2 = new UniqueGeneralEventList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list1.setGeneralEvents(null));

        List<GeneralEvent> inputList = new ArrayList<>();
        list1.add(event1);
        list1.add(event2);
        list1.add(event3);

        inputList.add(event1);
        inputList.add(event2);
        inputList.add(event3);

        // set inputList to list2 -> returns true
        list2.setGeneralEvents(inputList);
        assertEquals(list1, list2);

        // set inputList contains duplicate -> throws
        inputList.add(event1);
        assertThrows(DuplicateGeneralEventException.class, () -> list2.setGeneralEvents(inputList));
    }

    @Test
    public void equals() {
        UniqueGeneralEventList list1 = new UniqueGeneralEventList();

        // null -> returns false
        assertFalse(list1.equals(null));

        list1.add(event1);
        list1.add(event4);
        list1.add(event3);

        // different instance -> return false
        assertFalse(list1.equals(2));

        // same object -> returns true
        assertTrue(list1.equals(list1));

        UniqueGeneralEventList list2 = new UniqueGeneralEventList();
        list2.add(event1);
        list2.add(event4);
        list2.add(event3);

        // same attributes -> returns true
        assertTrue(list1.equals(list2));

        // different size -> returns false;
        list2.add(event2);
        assertFalse(list1.equals(list2));

        // different order -> return false
        list2.remove(event2);
        list2.remove(event1);
        list2.add(event1);
        assertFalse(list1.equals(list2));

        // different attributes -> return false
        list2.remove(event1);
        list1.remove(event1);
        list2.add(event2);
        list1.add(event1);
        assertFalse(list1.equals(list2));
    }
}
