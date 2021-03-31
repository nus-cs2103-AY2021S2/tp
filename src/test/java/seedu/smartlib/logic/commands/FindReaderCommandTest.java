package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.commons.core.Messages.MESSAGE_READERS_LISTED_OVERVIEW;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.testutil.TypicalModels.CARL;
import static seedu.smartlib.testutil.TypicalModels.ELLE;
import static seedu.smartlib.testutil.TypicalModels.FIONA;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindReaderCommand}.
 */
public class FindReaderCommandTest {
    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindReaderCommand findFirstCommand = new FindReaderCommand(firstPredicate);
        FindReaderCommand findSecondCommand = new FindReaderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindReaderCommand findFirstCommandCopy = new FindReaderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different reader -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noReaderFound() {
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_partialKeyword_noReaderFound() {
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kur");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_multipleKeywords_noReaderFound() {
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Hello Hi");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReaderList());
    }

    @Test
    public void execute_multipleKeywords_multipleReadersFound() {
        String expectedMessage = String.format(MESSAGE_READERS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindReaderCommand command = new FindReaderCommand(predicate);
        expectedModel.updateFilteredReaderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredReaderList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
