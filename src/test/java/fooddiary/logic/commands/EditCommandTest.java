package fooddiary.logic.commands;

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
import org.junit.jupiter.api.Test;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFoodDiary(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Entry editedEntry = new EntryBuilder().build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(model.getFoodDiary()), new UserPrefs());
        expectedModel.setEntry(model.getFilteredEntryList().get(0), editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEntry = Index.fromOneBased(model.getFilteredEntryList().size());
        Entry lastEntry = model.getFilteredEntryList().get(indexLastEntry.getZeroBased());

        EntryBuilder entryInList = new EntryBuilder(lastEntry);
        Entry editedEntry = entryInList.withName(CommandTestUtil.VALID_NAME_BOB).withRating(CommandTestUtil.VALID_RATING_BOB)
                .withTags(CommandTestUtil.VALID_TAG_WESTERN).build();

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withRating(CommandTestUtil.VALID_RATING_BOB).withTags(CommandTestUtil.VALID_TAG_WESTERN).build();

        EditCommand editCommand = new EditCommand(indexLastEntry, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(model.getFoodDiary()), new UserPrefs());
        expectedModel.setEntry(lastEntry, editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, new EditEntryDescriptor());
        Entry editedEntry = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(model.getFoodDiary()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showEntryAtIndex(model, INDEX_FIRST_ENTRY);

        Entry entryInFilteredList = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Entry editedEntry = new EntryBuilder(entryInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry);

        Model expectedModel = new ModelManager(new FoodDiary(model.getFoodDiary()), new UserPrefs());
        expectedModel.setEntry(model.getFilteredEntryList().get(0), editedEntry);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEntryUnfilteredList_failure() {
        Entry firstEntry = model.getFilteredEntryList().get(INDEX_FIRST_ENTRY.getZeroBased());
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(firstEntry).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ENTRY, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_duplicateEntryFilteredList_failure() {
        CommandTestUtil.showEntryAtIndex(model, INDEX_FIRST_ENTRY);

        // edit entry in filtered list into a duplicate in food diary
        Entry entryInList = model.getFoodDiary().getEntryList().get(INDEX_SECOND_ENTRY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY,
                new EditEntryDescriptorBuilder(entryInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    @Test
    public void execute_invalidEntryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEntryList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of food diary
     */
    @Test
    public void execute_invalidEntryIndexFilteredList_failure() {
        CommandTestUtil.showEntryAtIndex(model, INDEX_FIRST_ENTRY);
        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of food diary list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFoodDiary().getEntryList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ENTRY, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditEntryDescriptor copyDescriptor = new EditEntryDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ENTRY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ENTRY, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ENTRY, CommandTestUtil.DESC_BOB)));
    }

}
