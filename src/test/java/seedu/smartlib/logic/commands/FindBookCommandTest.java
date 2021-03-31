package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.commons.core.Messages.MESSAGE_BOOKS_LISTED_OVERVIEW;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.testutil.TypicalModels.HARRY_PORTER;
import static seedu.smartlib.testutil.TypicalModels.LEGACY;
import static seedu.smartlib.testutil.TypicalModels.PROMISE_LAND;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.book.BookNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBookCommand}.
 */
public class FindBookCommandTest {
    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void equals() {
        BookNameContainsKeywordsPredicate firstPredicate =
                new BookNameContainsKeywordsPredicate(Collections.singletonList("first"));
        BookNameContainsKeywordsPredicate secondPredicate =
                new BookNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindBookCommand findFirstCommand = new FindBookCommand(firstPredicate);
        FindBookCommand findSecondCommand = new FindBookCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBookCommand findFirstCommandCopy = new FindBookCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_partialKeyword_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("Kur");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_multipleKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("Hello Hi");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_multipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 3);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("POTTER LAND LEGACY");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HARRY_PORTER, PROMISE_LAND, LEGACY), model.getFilteredBookList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private BookNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BookNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

