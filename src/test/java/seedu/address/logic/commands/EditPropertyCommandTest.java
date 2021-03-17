package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;
import seedu.address.testutil.EditPropertyDescriptorBuilder;
import seedu.address.testutil.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPropertyCommand.
 */
public class EditPropertyCommandTest {

    private Model model = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Property editedProperty = new PropertyBuilder().build();
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, descriptor);

        String expectedMessage = String.format(editPropertyCommand.MESSAGE_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new PropertyBook(model.getPropertyBook()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProperty = Index.fromOneBased(model.getFilteredPropertyList().size());
        Property lastProperty = model.getFilteredPropertyList().get(indexLastProperty.getZeroBased());

        PropertyBuilder propertyInList = new PropertyBuilder(lastProperty);
        Property editedProperty = propertyInList.withName(VALID_NAME_BURGHLEY_DRIVE)
                .withPostal(VALID_POSTAL_BURGHLEY_DRIVE)
                .withType(VALID_TYPE_BURGHLEY_DRIVE).build();

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_BURGHLEY_DRIVE)
                .withPostalCode(VALID_POSTAL_BURGHLEY_DRIVE).withType(VALID_TYPE_BURGHLEY_DRIVE).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(indexLastProperty, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new PropertyBook(model.getPropertyBook()), new UserPrefs());
        expectedModel.setProperty(lastProperty, editedProperty);

        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptor());
        Property editedProperty = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new PropertyBook(model.getPropertyBook()), new UserPrefs());

        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property propertyInFilteredList = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        Property editedProperty = new PropertyBuilder(propertyInFilteredList)
                .withName(VALID_NAME_BURGHLEY_DRIVE).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder().withName(VALID_NAME_BURGHLEY_DRIVE).build());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new PropertyBook(model.getPropertyBook()), new UserPrefs());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePropertyUnfilteredList_failure() {
        Property firstProperty = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(firstProperty).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_SECOND_PROPERTY, descriptor);

        assertCommandFailure(editPropertyCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_duplicatePropertyFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        // edit Property in filtered list into a duplicate in property book
        Property propertyInList = model.getPropertyBook().getPropertyList().get(INDEX_SECOND_PROPERTY.getZeroBased());
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder(propertyInList).build());

        assertCommandFailure(editPropertyCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidPropertyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withName(VALID_NAME_BURGHLEY_DRIVE).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPropertyCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of property book
     */
    @Test
    public void execute_invalidPropertyIndexFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of property book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyBook().getPropertyList().size());

        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(outOfBoundIndex,
                new EditPropertyDescriptorBuilder().withName(VALID_NAME_BURGHLEY_DRIVE).build());

        assertCommandFailure(editPropertyCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPropertyCommand standardCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, DESC_MAYFAIR);

        // same values -> returns true
        EditPropertyDescriptor copyDescriptor = new EditPropertyDescriptor(DESC_MAYFAIR);
        EditPropertyCommand commandWithSameValues = new EditPropertyCommand(INDEX_FIRST_PROPERTY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAppointmentCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPropertyCommand(INDEX_SECOND_PROPERTY, DESC_MAYFAIR)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPropertyCommand(INDEX_FIRST_PROPERTY, DESC_BURGHLEY_DRIVE)));
    }

}
