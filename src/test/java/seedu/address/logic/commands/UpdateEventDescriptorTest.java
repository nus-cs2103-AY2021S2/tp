package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENT_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENT_2;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateEventCommand.UpdateEventDescriptor;

public class UpdateEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateEventDescriptor descriptorWithSameValues = new UpdateEventDescriptor(DESC_EVENT_1);
        assertTrue(DESC_EVENT_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EVENT_1.equals(DESC_EVENT_1));

        // null -> returns false
        assertFalse(DESC_EVENT_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_EVENT_1.equals(DESC_EVENT_2));

        // different description -> returns false
        UpdateEventCommand.UpdateEventDescriptor editedDesc1 =
                new UpdateEventCommand.UpdateEventDescriptor(DESC_EVENT_1);
        editedDesc1.setDescription("new deadline");
        assertFalse(DESC_EVENT_1.equals(editedDesc1));

        // different date -> returns false
        editedDesc1 = new UpdateEventCommand.UpdateEventDescriptor(DESC_EVENT_1);
        editedDesc1.setDate(LocalDate.of(2020, 2, 2));
        assertFalse(DESC_EVENT_1.equals(editedDesc1));

        // different time -> returns false
        editedDesc1 = new UpdateEventCommand.UpdateEventDescriptor(DESC_EVENT_1);
        editedDesc1.setTime(LocalTime.of(20, 30));
        assertFalse(DESC_EVENT_1.equals(editedDesc1));

        // different weekly -> returns false
        editedDesc1 = new UpdateEventCommand.UpdateEventDescriptor(DESC_EVENT_1);
        editedDesc1.setIsWeekly(true);
        assertFalse(DESC_EVENT_1.equals(editedDesc1));
    }
}
