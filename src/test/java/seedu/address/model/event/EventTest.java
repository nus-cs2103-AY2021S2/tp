package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_INTERVIEW;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.INTERVIEW;
import static seedu.address.testutil.TypicalEvents.ORIENTATION;

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
        assertTrue(INTERVIEW.isSameEvent(INTERVIEW));

        // null -> returns false
        assertFalse(INTERVIEW.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedInterview = new EventBuilder(INTERVIEW)
                .withStartDate(VALID_EVENT_STARTDATE_INTERVIEW).withStartTime(VALID_EVENT_STARTTIME_INTERVIEW)
                .withEndDate(VALID_EVENT_ENDDATE_INTERVIEW).withEndTime(VALID_EVENT_ENDTIME_INTERVIEW)
                .withTags(VALID_EVENT_TAG_INTERVIEW).withCategories(VALID_EVENT_CATEGORY_INTERVIEW)
                .build();
        assertTrue(INTERVIEW.isSameEvent(editedInterview));

        // different name, all other attributes same -> returns false
        editedInterview = new EventBuilder(INTERVIEW).withName(VALID_EVENT_NAME_ORIENTATION).build();
        assertFalse(INTERVIEW.isSameEvent(editedInterview));

        // name differs in case, all other attributes same -> returns false
        Event editedOrientation = new EventBuilder(ORIENTATION)
                .withName(VALID_EVENT_NAME_ORIENTATION.toLowerCase()).build();
        assertFalse(ORIENTATION.isSameEvent(editedOrientation));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_EVENT_NAME_ORIENTATION + " ";
        editedOrientation = new EventBuilder(ORIENTATION).withName(nameWithTrailingSpaces).build();
        assertFalse(ORIENTATION.isSameEvent(editedOrientation));

    }
}
