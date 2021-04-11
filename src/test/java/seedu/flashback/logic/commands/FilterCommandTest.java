package seedu.flashback.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.commons.core.Messages.MESSAGE_FLASHCARDS_FILTERED_OVERVIEW;
import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashback.testutil.Assert.assertThrows;
import static seedu.flashback.testutil.TypicalFlashcards.ACID;
import static seedu.flashback.testutil.TypicalFlashcards.ATP;
import static seedu.flashback.testutil.TypicalFlashcards.EINSTEIN;
import static seedu.flashback.testutil.TypicalFlashcards.MERGE;
import static seedu.flashback.testutil.TypicalFlashcards.NEWTON;
import static seedu.flashback.testutil.TypicalFlashcards.PYTHAGOREAN;
import static seedu.flashback.testutil.TypicalFlashcards.RECURSION;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.flashcard.FlashcardFilterPredicate;
/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterCommand(null));
    }

    @Test
    public void equals() {
        FlashcardFilterPredicate firstPredicate = new FlashcardFilterPredicate(Arrays.asList("alice"),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        FlashcardFilterPredicate secondPredicate = new FlashcardFilterPredicate(Arrays.asList("bob"),
                new ArrayList<>(), new ArrayList<>(), Arrays.asList("smart"));
        FilterCommand firstFilterCommand = new FilterCommand(firstPredicate);
        FilterCommand secondFilterCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));

        // different person -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));
    }

    @Test
    public void execute_zeroKeywords_allFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 7);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN, EINSTEIN, NEWTON, ATP, MERGE, RECURSION, ACID),
                model.getFilteredFlashcardList());
    }

    @Test
    public void execute_oneFieldKeyword_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 0);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(Arrays.asList("pythagoreans"),
                Arrays.asList("testing"), Arrays.asList("Low"), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleFieldKeyword_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 0);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(Arrays.asList("pythagorean"),
                Arrays.asList("testing"), Arrays.asList("Low"), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 2);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(Arrays.asList("pythagorean", "newton"),
                new ArrayList<>(), Arrays.asList("Low"), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN, NEWTON), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_oneFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 1);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(Arrays.asList("pythagorean", "newton"),
                Arrays.asList("math"), Arrays.asList("Low"), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 2);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(Arrays.asList("pythago", "ewton"),
                new ArrayList<>(), Arrays.asList("ow"), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN, NEWTON), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multiplePartialKeywords_oneFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_FILTERED_OVERVIEW, 1);
        FlashcardFilterPredicate predicate = new FlashcardFilterPredicate(Arrays.asList("pythago", "ton"),
                Arrays.asList("ma"), Arrays.asList("L"), new ArrayList<>());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PYTHAGOREAN), model.getFilteredFlashcardList());
    }
}
