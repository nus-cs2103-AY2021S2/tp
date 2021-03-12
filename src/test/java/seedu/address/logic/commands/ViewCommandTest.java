package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;

public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    //TODO: Add more test cases after the TypicalPerson.java is updated
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard viewCard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_FLASHCARD);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewCommand.MESSAGE_VIEW_SUCCESS, viewCard),
                INDEX_FIRST_FLASHCARD.getZeroBased());
        assertCommandSuccess(viewCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);
        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Flashcard viewCard = model.getFilteredFlashcardList()
                .get(INDEX_FIRST_FLASHCARD.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_FLASHCARD);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewCommand.MESSAGE_VIEW_SUCCESS, viewCard),
                INDEX_FIRST_FLASHCARD.getZeroBased());
        assertCommandSuccess(viewCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashBack().getCardList().size());
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);
        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_FLASHCARD);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_FLASHCARD);

        //same object => return true
        assertEquals(viewFirstCommand, viewFirstCommand);
        //same value => return true
        assertEquals(viewFirstCommand, new ViewCommand(INDEX_FIRST_FLASHCARD));
        assertEquals(viewSecondCommand, new ViewCommand(INDEX_SECOND_FLASHCARD));
        //different type
        assertNotEquals("view", viewFirstCommand);
        assertNotEquals(null, viewFirstCommand);
        assertNotEquals(viewFirstCommand, viewSecondCommand);
    }
}
