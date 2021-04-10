package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.DESC_BRIE;
import static chim.logic.commands.CommandTestUtil.DESC_MOZZARELLA;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZARELLA;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_4;
import static chim.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chim.logic.commands.EditOrderCommand.EditOrderDescriptor;
import chim.testutil.EditOrderDescriptorBuilder;

public class EditOrderDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditOrderDescriptor descriptor = new EditOrderDescriptor(DESC_BRIE);
        assertTrue(DESC_BRIE.equals(descriptor));

        // same object -> returns true
        assertTrue(DESC_BRIE.equals(DESC_BRIE));

        // null -> returns false
        assertFalse(DESC_BRIE.equals(null));

        // different type -> returns false
        assertFalse(DESC_BRIE.equals(7));

        // different values -> returns false
        assertFalse(DESC_BRIE.equals(DESC_MOZZARELLA));

        // different CheeseType -> returns false
        EditOrderDescriptor editedBrie = new EditOrderDescriptorBuilder(DESC_BRIE)
                .withCheeseType(VALID_CHEESE_TYPE_MOZZARELLA).build();

        assertFalse(DESC_BRIE.equals(editedBrie));

        // different quantity --> returns false
        editedBrie = new EditOrderDescriptorBuilder(DESC_BRIE)
                .withQuantity(VALID_QUANTITY_5).build();

        assertFalse(DESC_BRIE.equals(editedBrie));

        // different orderDate --> returns false
        editedBrie = new EditOrderDescriptorBuilder(DESC_BRIE)
                .withOrderDate(VALID_ORDER_DATE_4).build();

        assertFalse(DESC_BRIE.equals(editedBrie));
    }
}
