package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTaskDescriptorBuilder;


public class EditTaskDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor descriptorWithSameValues = new EditTaskCommand
                .EditTaskDescriptor(DESC_TASK1);
        assertTrue(DESC_TASK1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASK1.equals(DESC_TASK1));

        // null -> returns false
        assertFalse(DESC_TASK1.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASK1.equals(TITLE_DESC_TASK1));

        // different values -> returns false
        assertFalse(DESC_TASK1.equals(DESC_TASK2));

        // different title -> returns false
        EditTaskCommand.EditTaskDescriptor editedTask = new EditTaskDescriptorBuilder(DESC_TASK1)
                .withTitle(VALID_TITLE_MEETING).build();
        assertFalse(DESC_TASK1.equals(editedTask));

        // different description -> returns false
        editedTask = new EditTaskDescriptorBuilder(DESC_TASK1).withDescription(VALID_DESCRIPTION_MEETING).build();
        assertFalse(DESC_TASK1.equals(editedTask));

        // different deadline -> returns false
        editedTask = new EditTaskDescriptorBuilder(DESC_TASK1).withDeadline(VALID_DEADLINE_MEETING).build();
        assertFalse(DESC_TASK1.equals(editedTask));

        // different priority -> returns false
        editedTask = new EditTaskDescriptorBuilder(DESC_TASK2).withPriority("low").build();
        assertFalse(DESC_TASK2.equals(editedTask));

    }
}
