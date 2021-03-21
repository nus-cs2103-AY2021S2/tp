package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Statistics;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

public class StatsCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToShowStats = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Optional<Index> idx = Optional.of(INDEX_FIRST_FLASHCARD);
        StatsCommand statsCommand = new StatsCommand(idx);
        String expectedMessage = StatsCommand.MESSAGE_SHOW_CARD_STATS_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, idx, flashcardToShowStats.getStats());
        assertCommandSuccess(statsCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        StatsCommand statsCommand = new StatsCommand(Optional.of(outOfBoundIndex));
        assertCommandFailure(statsCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Flashcard flashcardToShowStats = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Optional<Index> idx = Optional.of(INDEX_FIRST_FLASHCARD);
        StatsCommand statsCommand = new StatsCommand(idx);
        String expectedMessage = StatsCommand.MESSAGE_SHOW_CARD_STATS_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, idx, flashcardToShowStats.getStats());
        assertCommandSuccess(statsCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of flash card list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashBack().getCardList().size());
        StatsCommand statsCommand = new StatsCommand(Optional.of(outOfBoundIndex));
        assertCommandFailure(statsCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noIndexUnfilteredList_success() {
        StatsCommand statsCommand = new StatsCommand(Optional.empty());
        String expectedMessage = StatsCommand.MESSAGE_SHOW_LIST_STATS_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, Optional.empty(),
                new Statistics(model.getFilteredFlashcardList()));
        assertCommandSuccess(statsCommand, model, expectedCommandResult, model);
    }

    @Test
    public void equals() {
        StatsCommand statsFirstCommand = new StatsCommand(Optional.of(INDEX_FIRST_FLASHCARD));
        StatsCommand statsSecondCommand = new StatsCommand(Optional.of(INDEX_SECOND_FLASHCARD));

        // same object -> returns true
        assertTrue(statsFirstCommand.equals(statsFirstCommand));

        // same values -> returns true
        StatsCommand statsFirstCommandCopy = new StatsCommand(Optional.of(INDEX_FIRST_FLASHCARD));
        assertTrue(statsFirstCommand.equals(statsFirstCommandCopy));

        // different types -> returns false
        assertFalse(statsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(statsFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(statsFirstCommand.equals(statsSecondCommand));
    }
}
