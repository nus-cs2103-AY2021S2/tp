package seedu.flashback.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.commons.core.Messages.MESSAGE_FLASHCARDS_FOUND_OVERVIEW;
import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalFlashcards.ATP;
import static seedu.flashback.testutil.TypicalFlashcards.EINSTEIN;
import static seedu.flashback.testutil.TypicalFlashcards.NEWTON;
import static seedu.flashback.testutil.TypicalFlashcards.PYTHAGOREAN;
import static seedu.flashback.testutil.TypicalFlashcards.RECURSION;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.flashcard.FlashcardContainsKeywordsPredicate;
/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindCommand(null));
    }

    @Test
    public void equals() {
        FlashcardContainsKeywordsPredicate firstPredicate =
                new FlashcardContainsKeywordsPredicate(Collections.singletonList("first"));
        FlashcardContainsKeywordsPredicate secondPredicate =
                new FlashcardContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different object -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FOUND_OVERVIEW, 0);
        FlashcardContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FOUND_OVERVIEW, 0);
        FlashcardContainsKeywordsPredicate predicate = preparePredicate("123 test testing");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FOUND_OVERVIEW, 5);
        FlashcardContainsKeywordsPredicate predicate = preparePredicate("newton biology equation random");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN, EINSTEIN, NEWTON, ATP, RECURSION), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FOUND_OVERVIEW, 5);
        FlashcardContainsKeywordsPredicate predicate = preparePredicate("new bio equa ran");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN, EINSTEIN, NEWTON, ATP, RECURSION), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code FlashcardContainsKeywordsPredicate}.
     */
    private FlashcardContainsKeywordsPredicate preparePredicate(String userInput) {
        return new FlashcardContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
