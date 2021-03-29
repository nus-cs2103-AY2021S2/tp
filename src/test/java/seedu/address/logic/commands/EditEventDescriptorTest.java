package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_SCHOOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FUN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_EVENTONE);
        assertTrue(DESC_EVENTONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EVENTONE.equals(DESC_EVENTONE));

        // null -> returns false
        assertFalse(DESC_EVENTONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_EVENTONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_EVENTONE.equals(DESC_EVENTTWO));

        // different name -> returns false
        EditEventDescriptor editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withName(VALID_EVENT_NAME_EVENTTWO).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));

        // different start date -> returns false
        editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));

        // different start time -> returns false
        editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withStartTime(VALID_EVENT_STARTTIME_EVENTTWO).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));

        // different end date -> returns false
        editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTTWO).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));

        // different end time -> returns false
        editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withEndTime(VALID_EVENT_ENDTIME_EVENTTWO).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));

        // different categories -> returns false
        editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withCategories(VALID_EVENT_CATEGORY_SCHOOL).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));

        // different tags -> returns false
        editedEventOne = new EditEventDescriptorBuilder(DESC_EVENTONE)
                .withTags(VALID_EVENT_TAG_FUN).build();
        assertFalse(DESC_EVENTONE.equals(editedEventOne));
    }
}
