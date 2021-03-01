package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.us.among.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.us.among.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.EditCommand.EditEndpointDescriptor;
import seedu.us.among.testutil.EditEndpointDescriptorBuilder;

public class EditEndpointDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEndpointDescriptor descriptorWithSameValues = new EditEndpointDescriptor(DESC_AMY);
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
        EditEndpointDescriptor editedAmy = new EditEndpointDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditEndpointDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
