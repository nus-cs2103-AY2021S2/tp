package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.smartlib.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.reader.Reader;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Reader readerToDelete = model.getFilteredReaderList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteReaderCommand.MESSAGE_DELETE_READER_SUCCESS, readerToDelete);

        ModelManager expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
        expectedModel.deleteReader(readerToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReaderList().size() + 1);
        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Reader readerToDelete = model.getFilteredReaderList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteReaderCommand.MESSAGE_DELETE_READER_SUCCESS, readerToDelete);

        Model expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
        expectedModel.deleteReader(readerToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartLib().getPersonList().size());

        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteReaderCommand deleteFirstCommand = new DeleteReaderCommand(INDEX_FIRST_PERSON);
        DeleteReaderCommand deleteSecondCommand = new DeleteReaderCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteReaderCommand deleteFirstCommandCopy = new DeleteReaderCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredReaderList().isEmpty());
    }
}
