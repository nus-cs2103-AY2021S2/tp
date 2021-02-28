package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalFlashcards.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

//    @Test
//    public void execute_validIndexUnfilteredList_success() {
//        Flashcard flashcardToDelete = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
//        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete);
//
//        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.deleteFlashcard(flashcardToDelete);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
//        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
//    }

//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
//
//        Flashcard flashcardToDelete = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
//        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete);
//
//        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
//        expectedModel.deleteFlashcard(flashcardToDelete);
//        showNoFlashcard(expectedModel);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }

//    @Test
//    public void execute_invalidIndexFilteredList_throwsCommandException() {
//        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
//
//        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFlashcardList().size());
//
//        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
//
//        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
//    }

//    @Test
//    public void equals() {
//        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);
//        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_FLASHCARD);
//
//        // same object -> returns true
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
//
//        // same values -> returns true
//        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_FLASHCARD);
//        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteFirstCommand.equals(null));
//
//        // different flashcard -> returns false
//        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
//    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFlashcard(Model model) {
        model.updateFilteredFlashcardList(p -> false);

        assertTrue(model.getFilteredFlashcardList().isEmpty());
    }
}
