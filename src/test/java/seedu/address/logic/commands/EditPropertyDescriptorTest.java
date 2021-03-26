package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BURGHLEY_DRIVE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.testutil.EditPropertyDescriptorBuilder;

public class EditPropertyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPropertyDescriptor descriptorWithSameValues = new EditPropertyDescriptor(DESC_MAYFAIR);
        assertTrue(DESC_MAYFAIR.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_MAYFAIR.equals(DESC_MAYFAIR));

        // null -> returns false
        assertFalse(DESC_MAYFAIR.equals(null));

        // different types -> returns false
        assertFalse(DESC_MAYFAIR.equals(5));

        // different values -> returns false
        assertFalse(DESC_MAYFAIR.equals(DESC_BURGHLEY_DRIVE));

        // different name -> returns false
        EditPropertyDescriptor editedAmy = new EditPropertyDescriptorBuilder(DESC_MAYFAIR)
                .withName(VALID_NAME_BURGHLEY_DRIVE).build();
        assertFalse(DESC_MAYFAIR.equals(editedAmy));

        // different postalcode -> returns false
        editedAmy = new EditPropertyDescriptorBuilder(DESC_MAYFAIR).withPostalCode(VALID_POSTAL_BURGHLEY_DRIVE).build();
        assertFalse(DESC_MAYFAIR.equals(editedAmy));

        // different deadline -> returns false
        editedAmy = new EditPropertyDescriptorBuilder(DESC_MAYFAIR).withDeadline(VALID_DEADLINE_BURGHLEY_DRIVE).build();
        assertFalse(DESC_MAYFAIR.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPropertyDescriptorBuilder(DESC_MAYFAIR).withAddress(VALID_ADDRESS_BURGHLEY_DRIVE).build();
        assertFalse(DESC_MAYFAIR.equals(editedAmy));

        // different type -> returns false
        editedAmy = new EditPropertyDescriptorBuilder(DESC_MAYFAIR).withType(VALID_TYPE_BURGHLEY_DRIVE).build();
        assertFalse(DESC_MAYFAIR.equals(editedAmy));
    }
}
