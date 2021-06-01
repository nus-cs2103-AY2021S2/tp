package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEADLINE_2;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateDeadlineCommand.UpdateDeadlineDescriptor;

public class UpdateDeadlineDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateDeadlineDescriptor descriptorWithSameValues = new UpdateDeadlineDescriptor(DESC_DEADLINE_1);
        assertTrue(DESC_DEADLINE_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DEADLINE_1.equals(DESC_DEADLINE_1));

        // null -> returns false
        assertFalse(DESC_DEADLINE_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_DEADLINE_1.equals(DESC_DEADLINE_2));

        // different description -> returns false
        UpdateDeadlineDescriptor editedDesc1 = new UpdateDeadlineDescriptor(DESC_DEADLINE_1);
        editedDesc1.setDescription("new deadline");
        assertFalse(DESC_DEADLINE_1.equals(editedDesc1));

        // different date -> returns false
        editedDesc1 = new UpdateDeadlineDescriptor(DESC_DEADLINE_1);
        editedDesc1.setDate(LocalDate.of(2020, 2, 2));
        assertFalse(DESC_DEADLINE_1.equals(editedDesc1));
    }
}
