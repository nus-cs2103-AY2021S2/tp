package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.EINSTEIN;
import static seedu.address.testutil.TypicalFlashcards.MERGE;
import static seedu.address.testutil.TypicalFlashcards.NEWTON;
import static seedu.address.testutil.TypicalFlashcards.RECURSION;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.CategoryContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCategoryCommand}.
 */
public class FindCategoryCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void equals() {
        CategoryContainsKeywordsPredicate firstPredicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("first"));
        CategoryContainsKeywordsPredicate secondPredicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCategoryCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCategoryCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCategoryCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        CategoryContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCategoryCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 4);
        CategoryContainsKeywordsPredicate predicate = preparePredicate("Physics Computer Science");
        FindCommand command = new FindCategoryCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EINSTEIN, NEWTON, MERGE, RECURSION), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code CategoryContainsKeywordsPredicate}.
     */
    private CategoryContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CategoryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
