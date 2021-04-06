package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandFailure;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithMultipleEntries;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithSingleEntry;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.AddOnCommand.AddOnToEntryDescriptor;
import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.Entry;
import fooddiary.testutil.AddOnEntryDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddOnCommand.
 */
public class AddOnCommandTest {

    private Model modelMultipleEntries = new ModelManager(getTypicalFoodDiaryWithMultipleEntries(), new UserPrefs());
    private Model modelSingleEntry = new ModelManager(getTypicalFoodDiaryWithSingleEntry(), new UserPrefs());

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        AddOnCommand addOnCommand = new AddOnCommand(INDEX_FIRST_ENTRY, new AddOnToEntryDescriptor());
        Entry updatedEntry = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(AddOnCommand.MESSAGE_NOT_ADDED_ON, updatedEntry);

        assertCommandFailure(addOnCommand, modelMultipleEntries, expectedMessage);
    }

    @Test
    public void execute_invalidEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelMultipleEntries.getFilteredEntryList().size() + 1);
        AddOnToEntryDescriptor descriptor = new AddOnEntryDescriptorBuilder()
                .withReviews(CommandTestUtil.VALID_REVIEW_B).build();
        AddOnCommand addOnCommand = new AddOnCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(addOnCommand, modelMultipleEntries, String.format(
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));
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
