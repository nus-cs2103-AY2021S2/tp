package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.AddOnCommand.AddOnToEntryDescriptor;
import fooddiary.model.FoodDiary;
import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.Entry;
import fooddiary.testutil.AddOnEntryDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddOnCommand.
 */
public class AddOnCommandTest {

    private Model model = new ModelManager(getTypicalFoodDiary(), new UserPrefs());

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        AddOnCommand addOnCommand = new AddOnCommand(INDEX_FIRST_ENTRY, new AddOnToEntryDescriptor());
        Entry updatedEntry = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(AddOnCommand.MESSAGE_ADDON_TO_ENTRY_SUCCESS, updatedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(model.getFoodDiary()), new UserPrefs());

        assertCommandSuccess(addOnCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEntryList().size() + 1);
        AddOnToEntryDescriptor descriptor = new AddOnEntryDescriptorBuilder()
                .withReviews(CommandTestUtil.VALID_REVIEW_B).build();
        AddOnCommand addOnCommand = new AddOnCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(addOnCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddOnCommand standardCommand = new AddOnCommand(INDEX_FIRST_ENTRY, CommandTestUtil.DESC_C);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddOnCommand(INDEX_SECOND_ENTRY, CommandTestUtil.DESC_C)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddOnCommand(INDEX_FIRST_ENTRY, CommandTestUtil.DESC_D)));
    }

}
