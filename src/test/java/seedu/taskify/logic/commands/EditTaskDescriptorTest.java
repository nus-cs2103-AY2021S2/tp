package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.taskify.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_DEBUGGING;

import org.junit.jupiter.api.Test;

import seedu.taskify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.testutil.EditTaskDescriptorBuilder;

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

        // different name -> returns false
        EditTaskDescriptor editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withName(VALID_NAME_CS2103T_TP).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_CS2103T_TP).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different status -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withStatus(StatusType.COMPLETED).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_DEBUGGING).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
