package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.CONSULTATION;
import static seedu.address.testutil.TypicalEntries.getTypicalEntriesList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryNameContainsKeywordsPredicate;
import seedu.address.testutil.EntryBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEntryCommand}.
 */
public class FindEntryCommandTest {
    private Model model = new ModelManager(getTypicalEntriesList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEntriesList(), new UserPrefs());

    @Test
    public void equals() {
        EntryNameContainsKeywordsPredicate predicate0 =
                new EntryNameContainsKeywordsPredicate(Collections.singletonList("a"));
        EntryNameContainsKeywordsPredicate predicate1 =
                new EntryNameContainsKeywordsPredicate(Collections.singletonList("a"));
        EntryNameContainsKeywordsPredicate predicate2 =
                new EntryNameContainsKeywordsPredicate(Collections.singletonList("b"));

        FindEntryCommand command0 = new FindEntryCommand(predicate0);
        FindEntryCommand command1 = new FindEntryCommand(predicate1);
        FindEntryCommand command2 = new FindEntryCommand(predicate2);

        assertEquals(command0, command0);
        assertEquals(command0, command1);
        assertNotEquals(command0, command2);
    }

    @Test
    public void execute_oneKeyword_noEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
        EntryNameContainsKeywordsPredicate predicate = preparePredicate("a");
        FindEntryCommand command = new FindEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntryList());
    }

    @Test
    public void execute_oneKeyword_oneEntryFound() {
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 1);
        EntryNameContainsKeywordsPredicate predicate = preparePredicate("CONSULTATION");
        FindEntryCommand command = new FindEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CONSULTATION), model.getFilteredEntryList());
    }

    @Test
    public void execute_oneKeyword_twoEntriesFound() {
        Entry impostor = new EntryBuilder(CONSULTATION)
                .withStartDate("2022-02-05 01:00")
                .withEndDate("2022-02-05 02:00")
                .build();
        model.addEntry(impostor);
        expectedModel.addEntry(impostor);
        String expectedMessage = String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 2);
        EntryNameContainsKeywordsPredicate predicate = preparePredicate("CONSULTATION");
        FindEntryCommand command = new FindEntryCommand(predicate);
        expectedModel.updateFilteredEntryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CONSULTATION, impostor), model.getFilteredEntryList());
    }

    /**
     * Parses {@code userInput} into a {@code EntryNameContainsKeywordsPredicate}.
     */
    private EntryNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EntryNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
