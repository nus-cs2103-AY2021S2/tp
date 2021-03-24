package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_ORIENTATION;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.INTERVIEW;
import static seedu.address.testutil.TypicalEvents.ORIENTATION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(ORIENTATION));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(INTERVIEW);
        assertTrue(uniqueEventList.contains(INTERVIEW));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEventList.add(INTERVIEW);
        Event editedInterview = new EventBuilder().withName(VALID_EVENT_NAME_INTERVIEW)
                .withStartDate(VALID_EVENT_STARTDATE_INTERVIEW).withStartTime(VALID_EVENT_STARTTIME_INTERVIEW)
                .withEndDate(VALID_EVENT_ENDDATE_INTERVIEW).withEndTime(VALID_EVENT_ENDTIME_INTERVIEW)
                .withTags(VALID_EVENT_TAG_INTERVIEW).withCategories(VALID_EVENT_CATEGORY_INTERVIEW)
                .build();
        assertTrue(uniqueEventList.contains(editedInterview));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.add(INTERVIEW);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(INTERVIEW));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, INTERVIEW));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(INTERVIEW, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(INTERVIEW, INTERVIEW));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() {
        uniqueEventList.add(INTERVIEW);
        uniqueEventList.setEvent(INTERVIEW, INTERVIEW);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(INTERVIEW);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasSameIdentity_success() {
        uniqueEventList.add(INTERVIEW);
        Event editedInterview = new EventBuilder(INTERVIEW).withEndTime(VALID_EVENT_ENDTIME_ORIENTATION)
                .withTags(VALID_EVENT_TAG_ORIENTATION).build();
        uniqueEventList.setEvent(INTERVIEW, editedInterview);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(editedInterview);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasDifferentIdentity_success() {
        uniqueEventList.add(INTERVIEW);
        uniqueEventList.setEvent(INTERVIEW, ORIENTATION);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(ORIENTATION);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        uniqueEventList.add(INTERVIEW);
        uniqueEventList.add(ORIENTATION);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(INTERVIEW, ORIENTATION));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(INTERVIEW));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(INTERVIEW);
        uniqueEventList.remove(INTERVIEW);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(INTERVIEW);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(ORIENTATION);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(INTERVIEW);
        List<Event> eventList = Collections.singletonList(ORIENTATION);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(ORIENTATION);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(INTERVIEW, INTERVIEW);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueEventList.asUnmodifiableObservableList()
                .remove(0));
    }
}
