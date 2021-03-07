package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_ESSENTIAL;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.EditCommand.EditItemDescriptor;
import seedu.storemando.testutil.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptor descriptorWithSameValues = new EditCommand.EditItemDescriptor(DESC_AMY);
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
        EditItemDescriptor editedAmy = new EditItemDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BANANA).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different quantity -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_AMY).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different expirydate -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_AMY).withExpiryDate(VALID_EXPIRYDATE_BANANA).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different location -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_AMY).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_ESSENTIAL).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
