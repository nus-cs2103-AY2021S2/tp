package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.*;
import static seedu.address.testutil.TypicalFlashcards.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.SortOptions;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand priorityDescending = new SortCommand(SortOptions.PRIORITY_DESCENDING);
        SortCommand priorityAscending = new SortCommand(SortOptions.PRIORITY_ASCENDING);

        // same object -> returns true
        assertTrue(priorityDescending.equals(priorityDescending));

        // same values -> returns true
        SortCommand priorityAscendingCopy = new SortCommand(SortOptions.PRIORITY_ASCENDING);
        assertTrue(priorityAscending.equals(priorityAscendingCopy));

        // different types -> returns false
        assertFalse(priorityAscending.equals(1));

        // null -> returns false
        assertFalse(priorityAscending.equals(null));

        // different commands -> returns false
        assertFalse(priorityAscending.equals(priorityDescending));
    }

    @Test
    public void execute_questionAscending_success() {
        String expectedMessage = MESSAGE_SORTED_QUESTION_ASCENDING;
        SortCommand sortCommand = new SortCommand(SortOptions.QUESTION_ASCENDING);
        expectedModel.sortFilteredFlashcardList(SortOptions.QUESTION_ASCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EINSTEIN, NEWTON, PYTHAGOREAN, ATP, RECURSION, ACID, MERGE),
                model.getFilteredFlashcardList());
    }

    @Test
    public void execute_questionDescending_success() {
        String expectedMessage = MESSAGE_SORTED_QUESTION_DESCENDING;
        SortCommand sortCommand = new SortCommand(SortOptions.QUESTION_DESCENDING);
        expectedModel.sortFilteredFlashcardList(SortOptions.QUESTION_DESCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MERGE, ACID, RECURSION, ATP, PYTHAGOREAN, NEWTON, EINSTEIN),
                model.getFilteredFlashcardList());
    }

    @Test
    public void execute_priorityAscending_success() {
        String expectedMessage = MESSAGE_SORTED_PRIORITY_ASCENDING;
        SortCommand sortCommand = new SortCommand(SortOptions.PRIORITY_ASCENDING);
        expectedModel.sortFilteredFlashcardList(SortOptions.PRIORITY_ASCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN, NEWTON, ATP, RECURSION, EINSTEIN, MERGE, ACID),
                model.getFilteredFlashcardList());
    }

    @Test
    public void execute_priorityDescending_success() {
        String expectedMessage = MESSAGE_SORTED_PRIORITY_DESCENDING;
        SortCommand sortCommand = new SortCommand(SortOptions.PRIORITY_DESCENDING);
        expectedModel.sortFilteredFlashcardList(SortOptions.PRIORITY_DESCENDING);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MERGE, ACID, EINSTEIN, PYTHAGOREAN, NEWTON, ATP, RECURSION),
                model.getFilteredFlashcardList());
    }
}
