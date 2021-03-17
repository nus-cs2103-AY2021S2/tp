package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.logic.commands.CommandTestUtil.showReaderAtIndex;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_READER;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_SECOND_READER;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

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

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Reader readerToDelete = model.getFilteredReaderList().get(INDEX_FIRST_READER.getZeroBased());
        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(INDEX_FIRST_READER);

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
        showReaderAtIndex(model, INDEX_FIRST_READER);

        Reader readerToDelete = model.getFilteredReaderList().get(INDEX_FIRST_READER.getZeroBased());
        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(INDEX_FIRST_READER);

        String expectedMessage = String.format(DeleteReaderCommand.MESSAGE_DELETE_READER_SUCCESS, readerToDelete);

        Model expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
        expectedModel.deleteReader(readerToDelete);
        showNoReader(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showReaderAtIndex(model, INDEX_FIRST_READER);

        Index outOfBoundIndex = INDEX_SECOND_READER;
        // ensures that outOfBoundIndex is still in bounds of smartLib list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartLib().getReaderList().size());

        DeleteReaderCommand deleteCommand = new DeleteReaderCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteReaderCommand deleteFirstCommand = new DeleteReaderCommand(INDEX_FIRST_READER);
        DeleteReaderCommand deleteSecondCommand = new DeleteReaderCommand(INDEX_SECOND_READER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteReaderCommand deleteFirstCommandCopy = new DeleteReaderCommand(INDEX_FIRST_READER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different reader -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoReader(Model model) {
        model.updateFilteredReaderList(p -> false);

        assertTrue(model.getFilteredReaderList().isEmpty());
    }
}
