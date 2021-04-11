package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.CONSULTATION;
import static seedu.address.testutil.TypicalEntries.DO_STUFF;
import static seedu.address.testutil.TypicalEntries.getTypicalEntriesList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.EntryTagsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterEntryCommand}.
 */
public class FilterEntryCommandTest {
    private Model model = new ModelManager(getTypicalEntriesList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEntriesList(), new UserPrefs());

    @Test
    public void equals() {
        EntryTagsContainKeywordsPredicate predicate0 =
                new EntryTagsContainKeywordsPredicate(Collections.singletonList("a"));
        EntryTagsContainKeywordsPredicate predicate1 =
                new EntryTagsContainKeywordsPredicate(Collections.singletonList("a"));
        EntryTagsContainKeywordsPredicate predicate2 =
                new EntryTagsContainKeywordsPredicate(Collections.singletonList("b"));

        FilterEntryCommand command0 = new FilterEntryCommand(predicate0);
        FilterEntryCommand command1 = new FilterEntryCommand(predicate1);
        FilterEntryCommand command2 = new FilterEntryCommand(predicate2);

        assertEquals(command0, command0);
        assertEquals(command0, command1);
        assertNotEquals(command0, command2);
    }

    @Test
    public void execute_oneKeyword_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
        EntryTagsContainKeywordsPredicate predicate = preparePredicate("a");
        FilterEntryCommand command = new FilterEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntryList());
    }

    @Test
    public void execute_oneKeyword_oneEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 1);
        EntryTagsContainKeywordsPredicate predicate = preparePredicate("history"); // case insensitive
        FilterEntryCommand command = new FilterEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CONSULTATION), model.getFilteredEntryList());
    }

    @Test
    public void execute_multipleKeywordsAndSearch_oneEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 1);
        EntryTagsContainKeywordsPredicate predicate = preparePredicate("important STUFF"); // case insensitive
        FilterEntryCommand command = new FilterEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DO_STUFF), model.getFilteredEntryList());
    }

    @Test
    public void execute_multipleKeywordsOrSearch_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
        EntryTagsContainKeywordsPredicate predicate = preparePredicate("History Math"); // case insensitive
        FilterEntryCommand command = new FilterEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntryList());
    }

    /**
     * Parses {@code userInput} into a {@code EntryTagsContainKeywordsPredicate}.
     */
    private EntryTagsContainKeywordsPredicate preparePredicate(String userInput) {
        return new EntryTagsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
