package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.DESC_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRED_EXPIRYDATE_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BANANA;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.storemando.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.storemando.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.commons.core.index.Index;
import seedu.storemando.logic.commands.EditCommand.EditItemDescriptor;
import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.Item;
import seedu.storemando.testutil.EditItemDescriptorBuilder;
import seedu.storemando.testutil.ItemBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Item editedItem = new ItemBuilder().build();
        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new StoreMando(model.getStoreMando()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_unexpiredSuccess() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());
        ItemBuilder itemInList = new ItemBuilder(lastItem);
        Item editedItem = itemInList.withName(VALID_NAME_BANANA).withQuantity(VALID_QUANTITY_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA)
            .withQuantity(VALID_QUANTITY_BANANA).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new StoreMando(model.getStoreMando()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_similarSuccess() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Index indexSecondLastItem = Index.fromOneBased(model.getFilteredItemList().size() - 1);
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());
        Item secondLastItem = model.getFilteredItemList().get(indexSecondLastItem.getZeroBased());
        ItemBuilder itemInList = new ItemBuilder(secondLastItem);
        Item editedItem = itemInList.withName(VALID_NAME_BANANA.toUpperCase()).withQuantity(VALID_QUANTITY_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
            .withName(VALID_NAME_BANANA.toUpperCase()).withQuantity(VALID_QUANTITY_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS
            + EditCommand.MESSAGE_SIMILAR_ITEM_WARNING, editedItem);

        Model expectedModel = new ModelManager(new StoreMando(model.getStoreMando()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_expiredSuccess() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());
        ItemBuilder itemInList = new ItemBuilder(lastItem);
        Item editedItem = itemInList.withName(VALID_NAME_BANANA).withQuantity(VALID_QUANTITY_BANANA)
            .withExpiryDate(VALID_EXPIRED_EXPIRYDATE_BANANA).withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA)
            .withQuantity(VALID_QUANTITY_BANANA).withExpiryDate(VALID_EXPIRED_EXPIRYDATE_BANANA)
            .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS
            + EditCommand.MESSAGE_ITEM_EXPIRED_WARNING, editedItem);

        Model expectedModel = new ModelManager(new StoreMando(model.getStoreMando()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, new EditCommand.EditItemDescriptor());
        String expectedMessage = String.format(EditCommand.MESSAGE_NO_CHANGE);
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_unexpiredSuccess() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        Item itemInFilteredList = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        Item editedItem = new ItemBuilder(itemInFilteredList).withName(VALID_NAME_BANANA).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
            new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new StoreMando(model.getStoreMando()), new UserPrefs());
        showItemAtIndex(expectedModel, INDEX_FIRST_ITEM);
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_expiredSuccess() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        Item itemInFilteredList = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        Item editedItem = new ItemBuilder(itemInFilteredList).withName(VALID_NAME_BANANA)
            .withExpiryDate(VALID_EXPIRED_EXPIRYDATE_BANANA).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
            new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA).withExpiryDate(VALID_EXPIRED_EXPIRYDATE_BANANA)
                .build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS
            + EditCommand.MESSAGE_ITEM_EXPIRED_WARNING, editedItem);

        Model expectedModel = new ModelManager(new StoreMando(model.getStoreMando()), new UserPrefs());
        showItemAtIndex(expectedModel, INDEX_FIRST_ITEM);
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        Item firstItem = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder(firstItem).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ITEM, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateItemFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        // edit item in filtered list into a duplicate in storemando
        Item itemInList = model.getStoreMando().getItemList().get(INDEX_SECOND_ITEM.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
            new EditItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_allSameFieldsSpecifiedUnfilteredList_failure() {
        Item firstItem = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder(firstItem).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_CHANGE);
    }

    @Test
    public void execute_someSameFieldsSpecifiedUnfilteredList_failure() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());

        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
            .withName(lastItem.getItemName().toString()).build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_NO_CHANGE);

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_allSameFieldsSpecifiedFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        // edit item in filtered list into a duplicate in storemando
        Item itemInList = model.getStoreMando().getItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
            new EditItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_CHANGE);
    }


    @Test
    public void execute_someSameFieldsSpecifiedFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        // edit item in filtered list into a duplicate in storemando
        Item itemInList = model.getStoreMando().getItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
            new EditItemDescriptorBuilder().withQuantity(itemInList.getQuantity().toString()).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_CHANGE);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItemList().size() + 1);
        EditCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of storemando
     */
    @Test
    public void execute_invalidItemIndexFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of storemando list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStoreMando().getItemList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditItemDescriptorBuilder().withName(VALID_NAME_BANANA).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ITEM, DESC_CHEESE);

        // same values -> returns true
        EditItemDescriptor copyDescriptor = new EditCommand.EditItemDescriptor(DESC_CHEESE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ITEM, DESC_CHEESE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ITEM, DESC_BANANA)));
    }

}
