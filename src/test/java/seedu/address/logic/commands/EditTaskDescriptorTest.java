package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_IMPORTANT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTaskDescriptorBuilder;


public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor descriptorWithSameValues =
                new EditTaskCommand.EditTaskDescriptor(DESC_TASKONE);
        assertTrue(DESC_TASKONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASKONE.equals(DESC_TASKONE));

        // null -> returns false
        assertFalse(DESC_TASKONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASKONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_TASKONE.equals(DESC_TASKTWO));

        // different name -> returns false
        EditTaskCommand.EditTaskDescriptor editedTaskOne = new EditTaskDescriptorBuilder(DESC_TASKONE)
                .withName(VALID_EVENT_NAME_EVENTTWO).build();
        assertFalse(DESC_TASKONE.equals(editedTaskOne));

        // different deadline -> returns false
        editedTaskOne = new EditTaskDescriptorBuilder(DESC_TASKONE)
                .withDeadline(VALID_TASK_DEADLINE_TASKTWO).build();
        assertFalse(DESC_EVENTONE.equals(editedTaskOne));

        // different priority -> returns false
        editedTaskOne = new EditTaskDescriptorBuilder(DESC_TASKONE)
                .withPriority(VALID_TASK_PRIORITY_TASKTWO).build();
        assertFalse(DESC_TASKONE.equals(editedTaskOne));

        // different categories -> returns false
        editedTaskOne = new EditTaskDescriptorBuilder(DESC_TASKONE)
                .withCategories(VALID_TASK_CATEGORY_PROJECT).build();
        assertFalse(DESC_TASKONE.equals(editedTaskOne));

        // different tags -> returns false
        editedTaskOne = new EditTaskDescriptorBuilder(DESC_TASKONE)
                .withTags(VALID_TASK_TAG_IMPORTANT).build();
        assertFalse(DESC_TASKONE.equals(editedTaskOne));
    }

}
