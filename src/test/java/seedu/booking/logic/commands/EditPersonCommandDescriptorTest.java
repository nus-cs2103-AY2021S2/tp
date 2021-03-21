package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_PERSON_COMMAND_DESCRIPTOR_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_PERSON_COMMAND_DESCRIPTOR_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.booking.testutil.EditPersonCommandDescriptorBuilder;

public class EditPersonCommandDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(VALID_PERSON_COMMAND_DESCRIPTOR_AMY);
        assertTrue(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(VALID_PERSON_COMMAND_DESCRIPTOR_AMY));

        // null -> returns false
        assertFalse(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(null));

        // different types -> returns false
        assertFalse(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(5));

        // different values -> returns false
        assertFalse(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(VALID_PERSON_COMMAND_DESCRIPTOR_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy =
         new EditPersonCommandDescriptorBuilder(VALID_PERSON_COMMAND_DESCRIPTOR_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonCommandDescriptorBuilder(VALID_PERSON_COMMAND_DESCRIPTOR_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        assertFalse(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonCommandDescriptorBuilder(VALID_PERSON_COMMAND_DESCRIPTOR_AMY)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(VALID_PERSON_COMMAND_DESCRIPTOR_AMY.equals(editedAmy));

    }
}
