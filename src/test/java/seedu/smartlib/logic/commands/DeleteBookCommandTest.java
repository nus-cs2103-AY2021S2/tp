package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.smartlib.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.book.Book;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteBookCommand}.
 */
public class DeleteBookCommandTest {

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteBookCommand.MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete);

        ModelManager expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);

        assertCommandSuccess(deleteBookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(outOfBoundIndex);

        assertCommandFailure(deleteBookCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteBookCommand.MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete);

        Model expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);
        showNoBook(expectedModel);

        assertCommandSuccess(deleteBookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of smartLib list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSmartLib().getBookList().size());

        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(outOfBoundIndex);

        assertCommandFailure(deleteBookCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBookCommand deleteFirstCommand = new DeleteBookCommand(INDEX_FIRST_BOOK);
        DeleteBookCommand deleteSecondCommand = new DeleteBookCommand(INDEX_SECOND_BOOK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBookCommand deleteFirstCommandCopy = new DeleteBookCommand(INDEX_FIRST_BOOK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different reader -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
    /**
     * Updates {@code model}'s filtered book list to show no book.
     */
    private void showNoBook(Model model) {
        model.updateFilteredBookList(p -> false);

        assertTrue(model.getFilteredBookList().isEmpty());
    }
}

