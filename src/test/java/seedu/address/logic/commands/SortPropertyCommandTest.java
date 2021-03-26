package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ASC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROPERTY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_PROPERTY_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_PROPERTY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_ASC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortPropertyCommand;
import seedu.address.logic.commands.sort.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;
import seedu.address.testutil.SortPropertyDescriptorBuilder;


public class SortPropertyCommandTest {

    private Model model = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_sortByDeadlineInAscendingOrder_success() {
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_ASC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE)
                .build();
        SortPropertyCommand sortPropertyCommand = new SortPropertyCommand(descriptor);

        String expectedMessage = String.format(SortPropertyCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

        Comparator<Property> comparator = (o1, o2) -> o1.getDeadline().compareTo(o2.getDeadline());

        expectedModel.sortPropertyList(comparator);

        assertCommandSuccess(sortPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByDeadlineInDescendingOrder_success() {
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_DEADLINE)
                .build();
        SortPropertyCommand sortPropertyCommand = new SortPropertyCommand(descriptor);

        String expectedMessage = String.format(SortPropertyCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

        Comparator<Property> comparator = (o1, o2) -> -1 * o1.getDeadline().compareTo(o2.getDeadline());

        expectedModel.sortPropertyList(comparator);

        assertCommandSuccess(sortPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByNameInAscendingOrder_success() {
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_ASC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_NAME)
                .build();
        SortPropertyCommand sortPropertyCommand = new SortPropertyCommand(descriptor);

        String expectedMessage = String.format(SortPropertyCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

        Comparator<Property> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

        expectedModel.sortPropertyList(comparator);

        assertCommandSuccess(sortPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByNameInDescendingOrder_success() {
        SortPropertyDescriptor descriptor = new SortPropertyDescriptorBuilder()
                .withSortingOrder(VALID_SORTING_ORDER_DESC)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_NAME)
                .build();
        SortPropertyCommand sortPropertyCommand = new SortPropertyCommand(descriptor);

        String expectedMessage = String.format(SortPropertyCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

        Comparator<Property> comparator = (o1, o2) -> -1 * o1.getName().compareTo(o2.getName());

        expectedModel.sortPropertyList(comparator);

        assertCommandSuccess(sortPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same values -> returns true
        SortPropertyDescriptor descriptorWithSameValues = new SortPropertyDescriptor(ASC_DEADLINE);
        assertTrue(ASC_DEADLINE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(ASC_DEADLINE.equals(ASC_DEADLINE));

        // null -> returns false
        assertFalse(ASC_DEADLINE.equals(null));

        // different types -> returns false
        assertFalse(ASC_DEADLINE.equals(5));

        // different values -> returns false
        assertFalse(ASC_DEADLINE.equals(DESC_PROPERTY_NAME));

        // different sorting orders -> returns false
        SortPropertyDescriptor editedDescriptor = new SortPropertyDescriptorBuilder(ASC_DEADLINE)
                .withSortingOrder(VALID_SORTING_ORDER_DESC).build();
        assertFalse(ASC_DEADLINE.equals(editedDescriptor));

        // different sorting keys -> returns false
        editedDescriptor = new SortPropertyDescriptorBuilder(ASC_DEADLINE)
                .withPropertySortingKey(VALID_SORTING_KEY_PROPERTY_NAME).build();
        assertFalse(ASC_DEADLINE.equals(editedDescriptor));
    }
}
