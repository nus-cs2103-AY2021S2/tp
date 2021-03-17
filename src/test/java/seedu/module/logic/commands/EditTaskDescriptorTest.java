package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.DESC_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_PRACTICAL;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.module.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_LAB);
        assertTrue(DESC_LAB.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_LAB.equals(DESC_LAB));

        // null -> returns false
        assertFalse(DESC_LAB.equals(null));

        // different types -> returns false
        assertFalse(DESC_LAB.equals(5));

        // different values -> returns false
        assertFalse(DESC_LAB.equals(DESC_PRACTICAL));

        // different name -> returns false
        EditTaskDescriptor editedLab = new EditTaskDescriptorBuilder(DESC_LAB)
                .withName(VALID_TASK_NAME_PRACTICAL).build();
        assertFalse(DESC_LAB.equals(editedLab));

        // different deadline -> returns false
        editedLab = new EditTaskDescriptorBuilder(DESC_LAB).withDeadline(VALID_DEADLINE_PRACTICAL).build();
        assertFalse(DESC_LAB.equals(editedLab));

        // different module -> returns false
        editedLab = new EditTaskDescriptorBuilder(DESC_LAB).withModule(VALID_MODULE_PRACTICAL).build();
        assertFalse(DESC_LAB.equals(editedLab));

        // different description -> returns false
        editedLab = new EditTaskDescriptorBuilder(DESC_LAB).withDescription(VALID_DESCRIPTION_PRACTICAL).build();
        assertFalse(DESC_LAB.equals(editedLab));

        // different tags -> returns false
        editedLab = new EditTaskDescriptorBuilder(DESC_LAB).withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertFalse(DESC_LAB.equals(editedLab));
    }
}
