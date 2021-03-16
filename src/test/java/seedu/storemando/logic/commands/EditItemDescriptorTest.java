package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.DESC_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.EditCommand.EditItemDescriptor;
import seedu.storemando.testutil.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptor descriptorWithSameValues = new EditCommand.EditItemDescriptor(DESC_CHEESE);
        assertTrue(DESC_CHEESE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHEESE.equals(DESC_CHEESE));

        // null -> returns false
        assertFalse(DESC_CHEESE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHEESE.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHEESE.equals(DESC_BANANA));

        // different name -> returns false
        EditItemDescriptor editedCheese = new EditItemDescriptorBuilder(DESC_CHEESE)
            .withName(VALID_NAME_BANANA).build();
        assertFalse(DESC_CHEESE.equals(editedCheese));

        // different quantity -> returns false
        editedCheese = new EditItemDescriptorBuilder(DESC_CHEESE).withQuantity(VALID_QUANTITY_BANANA).build();
        assertFalse(DESC_CHEESE.equals(editedCheese));

        // different expirydate -> returns false
        editedCheese = new EditItemDescriptorBuilder(DESC_CHEESE).withExpiryDate(VALID_EXPIRYDATE_BANANA).build();
        assertFalse(DESC_CHEESE.equals(editedCheese));

        // different location -> returns false
        editedCheese = new EditItemDescriptorBuilder(DESC_CHEESE).withLocation(VALID_LOCATION_BANANA).build();
        assertFalse(DESC_CHEESE.equals(editedCheese));

        // different tags -> returns false
        editedCheese = new EditItemDescriptorBuilder(DESC_CHEESE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_CHEESE.equals(editedCheese));
    }
}
