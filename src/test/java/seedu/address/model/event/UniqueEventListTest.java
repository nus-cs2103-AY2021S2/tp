package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FUN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CAMP;
import static seedu.address.testutil.TypicalEvents.COMPETITION;
import static seedu.address.testutil.TypicalEvents.DATE;
import static seedu.address.testutil.TypicalEvents.EVENTONE;
import static seedu.address.testutil.TypicalEvents.EVENTTWO;
import static seedu.address.testutil.TypicalEvents.HACKATHON;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalEvents.WORKSHOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    private UniqueEventList repopulatedEventList() {
        UniqueEventList populatedEventList = new UniqueEventList();
        populatedEventList.add(MEETING);
        populatedEventList.add(DATE);
        populatedEventList.add(CAMP);
        populatedEventList.add(HACKATHON);
        populatedEventList.add(WORKSHOP);
        populatedEventList.add(COMPETITION);
        return populatedEventList;
    }

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(EVENTTWO));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(EVENTONE);
        assertTrue(uniqueEventList.contains(EVENTONE));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEventList.add(EVENTONE);
        Event editedInterview = new EventBuilder().withName(VALID_EVENT_NAME_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
                .withTags(VALID_EVENT_TAG_FINAL).withCategories(VALID_EVENT_CATEGORY_WORK)
                .build();
        assertTrue(uniqueEventList.contains(editedInterview));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.add(EVENTONE);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(EVENTONE));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, EVENTONE));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(EVENTONE, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(EVENTONE, EVENTONE));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() {
        uniqueEventList.add(EVENTONE);
        uniqueEventList.setEvent(EVENTONE, EVENTONE);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(EVENTONE);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasSameIdentity_success() {
        uniqueEventList.add(EVENTONE);
        Event editedInterview = new EventBuilder(EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTTWO)
                .withTags(VALID_EVENT_TAG_FUN).build();
        uniqueEventList.setEvent(EVENTONE, editedInterview);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(editedInterview);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasDifferentIdentity_success() {
        uniqueEventList.add(EVENTONE);
        uniqueEventList.setEvent(EVENTONE, EVENTTWO);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(EVENTTWO);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        uniqueEventList.add(EVENTONE);
        uniqueEventList.add(EVENTTWO);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(EVENTONE, EVENTTWO));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(EVENTONE));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(EVENTONE);
        uniqueEventList.remove(EVENTONE);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(EVENTONE);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(EVENTTWO);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(EVENTONE);
        List<Event> eventList = Collections.singletonList(EVENTTWO);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(EVENTTWO);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(EVENTONE, EVENTONE);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueEventList.asUnmodifiableObservableList()
                .remove(0));
    }

    @Test
    public void sortEvents_nullList_nothingThrown() {
        assertDoesNotThrow(() ->
                uniqueEventList.sort(EventComparator.getAcceptedVar().get(0)));
    }

    @Test
    public void sortEvents_populatedList_allVariables() {
        for (String comparingVar : EventComparator.getAcceptedVar()) {
            EventComparator ec = new EventComparator();
            ec.setComparingVar(comparingVar);

            //build expected UniqueEventList
            ArrayList<Event> originalEvents = new ArrayList<>(
                    Arrays.asList(MEETING, DATE, CAMP, HACKATHON, WORKSHOP, COMPETITION));
            Collections.sort(originalEvents, ec);
            UniqueEventList expected = new UniqueEventList();
            expected.setEvents(originalEvents);

            //build actual UniqueEventList
            UniqueEventList actual = repopulatedEventList();
            actual.sort(comparingVar);

            assertEquals(actual, expected);
        }
    }
}
