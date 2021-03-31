package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EDIT_CHEESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANUFACTURE_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATURITY_DATE_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.cheese.CheeseType;
import seedu.address.testutil.EditCheeseDescriptorBuilder;

public class EditCheeseDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditCheeseCommand.EditCheeseDescriptor descriptorWithSameValues =
                new EditCheeseCommand.EditCheeseDescriptor(DESC_EDIT_CHEESE);
        assertTrue(DESC_EDIT_CHEESE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EDIT_CHEESE.equals(DESC_EDIT_CHEESE));

        // null -> returns false
        assertFalse(DESC_EDIT_CHEESE.equals(null));

        // different types -> returns false
        assertFalse(DESC_EDIT_CHEESE.equals(5));

        // different cheese type -> returns false
        EditCheeseCommand.EditCheeseDescriptor editedCheese = new EditCheeseDescriptorBuilder(DESC_EDIT_CHEESE)
                        .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)).build();
        assertFalse(DESC_EDIT_CHEESE.equals(editedCheese));

        // different manufacture date -> returns false
        editedCheese = new EditCheeseDescriptorBuilder(DESC_EDIT_CHEESE)
                .withManufactureDate(VALID_MANUFACTURE_DATE_1).build();
        assertFalse(DESC_EDIT_CHEESE.equals(editedCheese));

        // different maturity date -> returns false
        editedCheese = new EditCheeseDescriptorBuilder(DESC_EDIT_CHEESE)
                .withMaturityDate(VALID_MATURITY_DATE_1).build();
        assertFalse(DESC_EDIT_CHEESE.equals(editedCheese));

        // different expiry date -> returns false
        editedCheese = new EditCheeseDescriptorBuilder(DESC_EDIT_CHEESE)
                .withExpiryDate(VALID_EXPIRY_DATE_1).build();
        assertFalse(DESC_EDIT_CHEESE.equals(editedCheese));
    }
}
