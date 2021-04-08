package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZARELLA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_4;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCheeseAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FILTERED_CHEESE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CHEESE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_CHEESE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CHEESE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CHEESE;
import static seedu.address.testutil.TypicalModels.getTypicalChim;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Chim;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseType;
import seedu.address.testutil.CheeseBuilder;
import seedu.address.testutil.EditCheeseDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCheeseCommand.
 */
public class EditCheeseCommandTest {

    private Model model = new ModelManager(getTypicalChim(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Cheese editedCheese = new CheeseBuilder().withId(model.getFilteredCheeseList().get(2).getCheeseId()).build();
        EditCheeseCommand.EditCheeseDescriptor descriptor = new EditCheeseDescriptorBuilder(editedCheese).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(INDEX_THIRD_CHEESE, descriptor);

        String expectedMessage = String.format(EditCheeseCommand.MESSAGE_EDIT_CHEESE_SUCCESS, editedCheese);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setCheese(model.getFilteredCheeseList().get(2), editedCheese);
        expectedModel.setPanelToCheeseList();

        assertCommandSuccess(editCheeseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Cheese cheese = model.getFilteredCheeseList().get(INDEX_THIRD_CHEESE.getZeroBased());
        Cheese editedCheese = new CheeseBuilder(cheese)
                .withCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)
                .withExpiryDate(VALID_EXPIRY_DATE_4).build();
        EditCheeseCommand.EditCheeseDescriptor descriptor = new EditCheeseDescriptorBuilder()
                .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA))
                .withExpiryDate(VALID_EXPIRY_DATE_4).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(INDEX_THIRD_CHEESE, descriptor);

        String expectedMessage = String.format(EditCheeseCommand.MESSAGE_EDIT_CHEESE_SUCCESS, editedCheese);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setCheese(model.getFilteredCheeseList().get(2), editedCheese);
        expectedModel.setPanelToCheeseList();

        assertCommandSuccess(editCheeseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(INDEX_THIRD_CHEESE,
                new EditCheeseCommand.EditCheeseDescriptor());
        Cheese editedCheese = model.getFilteredCheeseList().get(INDEX_THIRD_CHEESE.getZeroBased());

        String expectedMessage = String.format(EditCheeseCommand.MESSAGE_EDIT_CHEESE_SUCCESS, editedCheese);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setPanelToCheeseList();

        assertCommandSuccess(editCheeseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCheeseAtIndex(model, INDEX_THIRD_CHEESE);

        Cheese cheeseInFilteredList = model.getFilteredCheeseList().get(INDEX_FILTERED_CHEESE.getZeroBased());
        Cheese editedCheese = new CheeseBuilder(cheeseInFilteredList)
                .withCheeseType(VALID_CHEESE_TYPE_MOZZARELLA).build();
        EditCheeseCommand.EditCheeseDescriptor descriptor = new EditCheeseDescriptorBuilder()
                .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(INDEX_FILTERED_CHEESE, descriptor);

        String expectedMessage = String.format(EditCheeseCommand.MESSAGE_EDIT_CHEESE_SUCCESS, editedCheese);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setCheese(model.getFilteredCheeseList().get(0), editedCheese);
        expectedModel.setPanelToCheeseList();

        assertCommandSuccess(editCheeseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignedCheeseUnfilteredList_failure() {
        Cheese cheese = model.getFilteredCheeseList().get(INDEX_FIRST_CHEESE.getZeroBased());
        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder(cheese).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(INDEX_FIRST_CHEESE, descriptor);

        assertCommandFailure(editCheeseCommand, model, EditCheeseCommand.MESSAGE_CHEESE_IS_ASSIGNED);
    }

    @Test
    public void execute_assignedCheeseFilteredList_failure() {
        showCheeseAtIndex(model, INDEX_SECOND_CHEESE);

        Cheese cheese = model.getFilteredCheeseList().get(INDEX_FILTERED_CHEESE.getZeroBased());
        EditCheeseCommand.EditCheeseDescriptor descriptor =
                new EditCheeseDescriptorBuilder(cheese).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(INDEX_FILTERED_CHEESE, descriptor);

        assertCommandFailure(editCheeseCommand, model, EditCheeseCommand.MESSAGE_CHEESE_IS_ASSIGNED);
    }

    @Test
    public void execute_invalidCheeseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCheeseList().size() + 1);

        EditCheeseCommand.EditCheeseDescriptor descriptor = new EditCheeseDescriptorBuilder()
                .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCheeseCommand, model, Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of cheese list
     */
    @Test
    public void execute_invalidCheeseIndexFilteredList_failure() {
        showCheeseAtIndex(model, INDEX_THIRD_CHEESE);
        Index outOfBoundIndex = INDEX_FOURTH_CHEESE;

        // ensures that outOfBoundIndex is still in bounds of cheese list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getChim().getCheeseList().size());

        EditCheeseCommand.EditCheeseDescriptor descriptor = new EditCheeseDescriptorBuilder()
                .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)).build();
        EditCheeseCommand editCheeseCommand = new EditCheeseCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCheeseCommand, model, Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditCheeseCommand.EditCheeseDescriptor descriptor = new EditCheeseDescriptorBuilder()
                .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_MOZZARELLA)).build();
        final EditCheeseCommand standardCommand = new EditCheeseCommand(INDEX_THIRD_CHEESE, descriptor);

        // same values -> returns true
        EditCheeseCommand.EditCheeseDescriptor copyDescriptor =
                new EditCheeseCommand.EditCheeseDescriptor(descriptor);
        EditCheeseCommand commandWithSameValues = new EditCheeseCommand(INDEX_THIRD_CHEESE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCheeseCommand(INDEX_FOURTH_CHEESE, descriptor)));

        // different descriptor -> returns false
        EditCheeseCommand.EditCheeseDescriptor newDescriptor = new EditCheeseDescriptorBuilder()
                .withCheeseType(CheeseType.getCheeseType(VALID_CHEESE_TYPE_FETA)).build();
        assertFalse(standardCommand.equals(new EditCheeseCommand(INDEX_THIRD_CHEESE, newDescriptor)));
    }
}
