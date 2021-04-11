package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRESSCODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.testutil.EditGarmentDescriptorBuilder;

public class EditGarmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditGarmentDescriptor descriptorWithSameValues = new EditGarmentDescriptor(DESC_AMY);
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
        EditGarmentDescriptor editedAmy = new EditGarmentDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different size -> returns false
        editedAmy = new EditGarmentDescriptorBuilder(DESC_AMY).withSize(VALID_SIZE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different colour -> returns false
        editedAmy = new EditGarmentDescriptorBuilder(DESC_AMY).withColour(VALID_COLOUR_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditGarmentDescriptorBuilder(DESC_AMY).withDressCode(VALID_DRESSCODE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditGarmentDescriptorBuilder(DESC_AMY).withDescriptions(VALID_DESCRIPTION_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
