package seedu.address.logic.commands;

import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.NameContainsAllKeywordsPredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static fooddiary.commons.core.Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW;
import static fooddiary.testutil.TypicalEntries.CARL;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAllCommand}.
 */
public class FindAllCommandTest {
    private Model model = new ModelManager(getTypicalFoodDiary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFoodDiary(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsAllKeywordsPredicate firstPredicate =
                new NameContainsAllKeywordsPredicate(Collections.singletonList("first"));
        NameContainsAllKeywordsPredicate secondPredicate =
                new NameContainsAllKeywordsPredicate(Collections.singletonList("second"));

        FindAllCommand findFirstCommand = new FindAllCommand(firstPredicate);
        FindAllCommand findSecondCommand = new FindAllCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAllCommand findFirstCommandCopy = new FindAllCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different entry -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
        NameContainsAllKeywordsPredicate predicate = preparePredicate(" ");
        FindAllCommand command = new FindAllCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntryList());
    }

    @Test
    public void execute_multipleKeywords_singleEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 1);
        NameContainsAllKeywordsPredicate predicate = preparePredicate("Carl Kurz");
        FindAllCommand command = new FindAllCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredEntryList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsAllKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsAllKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
