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
        // EP: multiple spaces
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_partialKeyword_noBookFound() {
        //EP: incomplete keyword
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("Kur");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_multipleKeywords_noBookFound() {
        //EP: incomplete keywords
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("Har Pot");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_multipleKeywords_multipleBooksFound() {
        //EP: keywords matching multiple book titles
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 3);
        BookNameContainsKeywordsPredicate predicate = preparePredicate("POTTER LAND LEGACY");
        FindBookCommand command = new FindBookCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HARRY_PORTER, PROMISE_LAND, LEGACY), model.getFilteredBookList());

        //EP: keywords matching multiple book labels(genre)
        String expectedMessage2 = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        BookNameContainsKeywordsPredicate predicate2 = preparePredicate("Novel");
        FindBookCommand command2 = new FindBookCommand(predicate2);
        expectedModel.updateFilteredBookList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Arrays.asList(PROMISE_LAND, LEGACY), model.getFilteredBookList());

        //EP: keywords matching multiple book labels(author)
        String expectedMessage3 = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        BookNameContainsKeywordsPredicate predicate3 = preparePredicate("Barack Obama");
        FindBookCommand command3 = new FindBookCommand(predicate3);
        expectedModel.updateFilteredBookList(predicate3);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        assertEquals(Arrays.asList(PROMISE_LAND), model.getFilteredBookList());

        //EP: keywords matching multiple book labels(publisher)
        String expectedMessage4 = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        BookNameContainsKeywordsPredicate predicate4 = preparePredicate("Scholastic");
        FindBookCommand command4 = new FindBookCommand(predicate4);
        expectedModel.updateFilteredBookList(predicate4);
        assertCommandSuccess(command4, model, expectedMessage4, expectedModel);
        assertEquals(Arrays.asList(HARRY_PORTER), model.getFilteredBookList());

        //EP: keywords matching multiple book labels(isbn)
        String expectedMessage5 = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        BookNameContainsKeywordsPredicate predicate5 = preparePredicate("9781472103536");
        FindBookCommand command5 = new FindBookCommand(predicate5);
        expectedModel.updateFilteredBookList(predicate5);
        assertCommandSuccess(command5, model, expectedMessage5, expectedModel);
        assertEquals(Arrays.asList(LEGACY), model.getFilteredBookList());

    }

    /**
     * Parses {@code userInput} into a {@code BookNameContainsKeywordsPredicate}.
     */
    private BookNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BookNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}

