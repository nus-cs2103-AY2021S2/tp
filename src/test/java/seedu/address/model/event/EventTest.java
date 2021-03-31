package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FINAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENTONE;
import static seedu.address.testutil.TypicalEvents.EVENTTWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getTags().remove(0));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(EVENTONE.isSameEvent(EVENTONE));

        // null -> returns false
        assertFalse(EVENTONE.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedInterview = new EventBuilder(EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
                .withTags(VALID_EVENT_TAG_FINAL).withCategories(VALID_EVENT_CATEGORY_WORK)
                .build();
        assertTrue(EVENTONE.isSameEvent(editedInterview));

        // different name, all other attributes same -> returns false
        editedInterview = new EventBuilder(EVENTONE).withName(VALID_EVENT_NAME_EVENTTWO).build();
        assertFalse(EVENTONE.isSameEvent(editedInterview));

        // name differs in case, all other attributes same -> returns false
        Event editedOrientation = new EventBuilder(EVENTTWO)
                .withName(VALID_EVENT_NAME_EVENTTWO.toLowerCase()).build();
        assertFalse(EVENTTWO.isSameEvent(editedOrientation));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_EVENT_NAME_EVENTTWO + " ";
        editedOrientation = new EventBuilder(EVENTTWO).withName(nameWithTrailingSpaces).build();
        assertFalse(EVENTTWO.isSameEvent(editedOrientation));

    }
}
