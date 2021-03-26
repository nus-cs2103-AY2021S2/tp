package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ASC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROPERTY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_PROPERTY_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_ASC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.testutil.SortPropertyDescriptorBuilder;

public class SortPropertyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        SortPropertyDescriptor descriptorWithSameValues = new SortPropertyDescriptor(DESC_PROPERTY_NAME);
        assertTrue(DESC_PROPERTY_NAME.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PROPERTY_NAME.equals(DESC_PROPERTY_NAME));

        // null -> returns false
        assertFalse(DESC_PROPERTY_NAME.equals(null));

        // different types -> returns false
        assertFalse(DESC_PROPERTY_NAME.equals(5));

        // different values -> returns false
        assertFalse(DESC_PROPERTY_NAME.equals(ASC_DEADLINE));

        // different sorting orders -> returns false
        SortPropertyDescriptor ascName = new SortPropertyDescriptorBuilder(DESC_PROPERTY_NAME)
                .withSortingOrder(VALID_SORTING_ORDER_ASC).build();
        assertFalse(DESC_PROPERTY_NAME.equals(ascName));

        // different sorting keys -> returns false
        SortPropertyDescriptor desDatetime = new SortPropertyDescriptorBuilder(DESC_PROPERTY_NAME)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE).build();
        assertFalse(DESC_PROPERTY_NAME.equals(desDatetime));
    }
}
