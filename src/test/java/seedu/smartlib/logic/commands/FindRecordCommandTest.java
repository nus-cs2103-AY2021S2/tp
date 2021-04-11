package seedu.smartlib.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.commons.core.Messages.MESSAGE_RECORD_LISTED_OVERVIEW;
import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;
import static seedu.smartlib.testutil.TypicalModels.RECORD_B;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.record.RecordContainsBookNamePredicate;

public class FindRecordCommandTest {

    private Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSmartLib(), new UserPrefs());

    @Test
    public void equals() {
        RecordContainsBookNamePredicate firstPredicate =
                new RecordContainsBookNamePredicate(Collections.singletonList("first"));
        RecordContainsBookNamePredicate secondPredicate =
                new RecordContainsBookNamePredicate(Collections.singletonList("second"));

        FindRecordCommand findFirstCommand = new FindRecordCommand(firstPredicate);
        FindRecordCommand findSecondCommand = new FindRecordCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRecordCommand findFirstCommandCopy = new FindRecordCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different record -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

    }

    @Test
    public void execute_zeroKeywords_noRecordFound() {
        // EP: multiple spaces
        String expectedMessage = String.format(MESSAGE_RECORD_LISTED_OVERVIEW, 0);
        RecordContainsBookNamePredicate predicate = preparePredicate(" ");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecordList());
    }

    @Test
    public void execute_partialKeyword_noRecordFound() {
        //EP: incomplete keyword
        String expectedMessage = String.format(MESSAGE_RECORD_LISTED_OVERVIEW, 0);
        RecordContainsBookNamePredicate predicate = preparePredicate("Har");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecordList());
    }

    @Test
    public void execute_multipleKeywords_noRecordFound() {
        //EP: incomplete keywords
        String expectedMessage = String.format(MESSAGE_RECORD_LISTED_OVERVIEW, 0);
        RecordContainsBookNamePredicate predicate = preparePredicate("Ma Ru");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecordList());
    }

    @Test
    public void execute_multipleKeywords_multipleRecordsFound() {
        //EP: keywords matching multiple book titles
        String expectedMessage = String.format(MESSAGE_RECORD_LISTED_OVERVIEW, 2);
        RecordContainsBookNamePredicate predicate = preparePredicate("HARRY MAZE");
        FindRecordCommand command = new FindRecordCommand(predicate);
        expectedModel.updateFilteredRecordList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RECORD_A, RECORD_B), model.getFilteredRecordList());
    }

    /**
     * Parses {@code userInput} into a {@code RecordContainsBookNamePredicate}.
     */
    private RecordContainsBookNamePredicate preparePredicate(String userInput) {
        return new RecordContainsBookNamePredicate(Arrays.asList(userInput.split("\\s+")));
    }


}
