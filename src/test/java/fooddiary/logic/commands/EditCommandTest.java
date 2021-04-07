package fooddiary.logic.commands;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandFailure;
import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithMultipleEntries;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiaryWithSingleEntry;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.EditCommand.EditEntryDescriptor;
import fooddiary.model.FoodDiary;
import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.Entry;
import fooddiary.testutil.EditEntryDescriptorBuilder;
import fooddiary.testutil.EntryBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model modelMultipleEntries = new ModelManager(getTypicalFoodDiaryWithMultipleEntries(), new UserPrefs());
    private Model modelSingleEntry = new ModelManager(getTypicalFoodDiaryWithSingleEntry(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Entry editedEntry = new EntryBuilder().build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(modelMultipleEntries.getFoodDiary()), new UserPrefs());
        expectedModel.setEntry(modelMultipleEntries.getFilteredEntryList().get(0), editedEntry);

        assertCommandSuccess(editCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEntry = Index.fromOneBased(modelMultipleEntries.getFilteredEntryList().size());
        Entry lastEntry = modelMultipleEntries.getFilteredEntryList().get(indexLastEntry.getZeroBased());

        EntryBuilder entryInList = new EntryBuilder(lastEntry);
        Entry editedEntry = entryInList.withName(CommandTestUtil.VALID_NAME_B)
                .withRating(CommandTestUtil.VALID_RATING_B)
                .withTagCategories(CommandTestUtil.VALID_TAG_CATEGORY_WESTERN).build();

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_B)
                .withRating(CommandTestUtil.VALID_RATING_B)
                .withTagCategories(CommandTestUtil.VALID_TAG_CATEGORY_WESTERN).build();

        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(modelMultipleEntries.getFoodDiary()), new UserPrefs());
        expectedModel.setEntry(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showEntryAtIndex(modelMultipleEntries, INDEX_FIRST_ENTRY);

        Entry entryInFilteredList = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Entry editedEntry = new EntryBuilder(entryInFilteredList).withName(CommandTestUtil.VALID_NAME_B).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_B).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(modelMultipleEntries.getFoodDiary()), new UserPrefs());
        expectedModel.setEntry(modelMultipleEntries.getFilteredEntryList().get(0), editedEntry);

        assertCommandSuccess(editCommand, modelMultipleEntries, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, new EditEntryDescriptor());
        Entry editedEntry = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());

        //assertCommandSuccess(editCommand, modelMultipleEntries, expectedMessage, expectedModel);
        assertCommandFailure(editCommand, modelMultipleEntries, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_duplicateEntryUnfilteredList_failure() {
        Entry firstEntry = modelMultipleEntries.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(firstEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ENTRY, descriptor);

        assertCommandFailure(editCommand, modelMultipleEntries, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicateEntryFilteredList_failure() {
        CommandTestUtil.showEntryAtIndex(modelMultipleEntries, INDEX_FIRST_ENTRY);

        // edit entry in filtered list into a duplicate in food diary
        Entry entryInList = modelMultipleEntries.getFoodDiary().getEntryList().get(INDEX_SECOND_ENTRY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder(entryInList).build());

        assertCommandFailure(editCommand, modelMultipleEntries, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_invalidEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(modelMultipleEntries.getFilteredEntryList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_B).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        //Check when model has multiple entries (to check for plural message)
        assertCommandFailure(editCommand, modelMultipleEntries, String.format(
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));

        //Check when model has single entry (to check for single message)
        assertCommandFailure(editCommand, modelSingleEntry,
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of food diary
     */
    @Test
    public void execute_invalidEntryIndexFilteredList_failure() {
        CommandTestUtil.showEntriesAtIndexes(modelMultipleEntries, INDEX_FIRST_ENTRY, INDEX_SECOND_ENTRY);
        Index outOfBoundIndex = INDEX_THIRD_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < modelMultipleEntries.getFoodDiary().getEntryList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_B).build());

        assertCommandFailure(editCommand, modelMultipleEntries, String.format(
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                modelMultipleEntries.getFilteredEntryList().size()));

        assertCommandFailure(editCommand, modelSingleEntry,
                Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ENTRY, CommandTestUtil.DESC_A);

        // same values -> returns true
        EditEntryDescriptor copyDescriptor = new EditEntryDescriptor(CommandTestUtil.DESC_A);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ENTRY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ENTRY, CommandTestUtil.DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ENTRY, CommandTestUtil.DESC_B)));
    }

}
