package seedu.address.logic.commands.remindercommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REMINDER_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REMINDER_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DATE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMINDER_DESC_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.remindercommands.EditReminderCommand.EditReminderDescriptor;
import seedu.address.testutil.EditReminderDescriptorBuilder;

public class EditReminderDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditReminderDescriptor descriptorWithSameValues = new EditReminderDescriptor(DESC_REMINDER_MATHS);
        assertTrue(DESC_REMINDER_MATHS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_REMINDER_MATHS.equals(DESC_REMINDER_MATHS));

        // null -> returns false
        assertFalse(DESC_REMINDER_MATHS.equals(null));

        // different types -> returns false
        assertFalse(DESC_REMINDER_MATHS.equals(5));

        // different values -> returns false
        assertFalse(DESC_REMINDER_MATHS.equals(DESC_REMINDER_SCIENCE));

        // different desc -> returns false
        EditReminderDescriptor editedMaths = new EditReminderDescriptorBuilder(DESC_REMINDER_MATHS)
                .withDescription(VALID_REMINDER_DESC_TWO).build();
        assertFalse(DESC_REMINDER_MATHS.equals(editedMaths));

        // different date -> returns false
        editedMaths = new EditReminderDescriptorBuilder(DESC_REMINDER_MATHS)
                .withReminderDate(VALID_REMINDER_DATE_TWO).build();
        assertFalse(DESC_REMINDER_MATHS.equals(editedMaths));
    }
}
