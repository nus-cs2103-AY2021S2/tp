package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different title -> returns false
        EditTaskDescriptor editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withTitle(VALID_TITLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different deadline -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        /*
        // different recurring schedule -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withRecurringSchedule(VALID_RECURRINGSCHEDULE_BOB).build();
        assertTrue(DESC_AMY.equals(editedAmy));
         */

        // different description -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
