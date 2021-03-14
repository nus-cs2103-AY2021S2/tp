package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.ACID;
import static seedu.address.testutil.TypicalFlashcards.EINSTEIN;
import static seedu.address.testutil.TypicalFlashcards.MERGE;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.PriorityContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPriorityCommand}.
 */
public class FindPriorityCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void equals() {
        PriorityContainsKeywordsPredicate firstPredicate =
                new PriorityContainsKeywordsPredicate(Collections.singletonList("first"));
        PriorityContainsKeywordsPredicate secondPredicate =
                new PriorityContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindPriorityCommand(firstPredicate);
        FindCommand findSecondCommand = new FindPriorityCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindPriorityCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different card -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        PriorityContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindPriorityCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        PriorityContainsKeywordsPredicate predicate = preparePredicate("123 test random");
        FindCommand command = new FindPriorityCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        PriorityContainsKeywordsPredicate predicate = preparePredicate("high mid");
        FindCommand command = new FindPriorityCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EINSTEIN, MERGE, ACID), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        PriorityContainsKeywordsPredicate predicate = preparePredicate("hi m");
        FindCommand command = new FindPriorityCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EINSTEIN, MERGE, ACID), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code PriorityContainsKeywordsPredicate}.
     */
    private PriorityContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PriorityContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
