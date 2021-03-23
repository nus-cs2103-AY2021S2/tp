package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIFTH_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.student.MatriculationNumberContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void equals() {
        MatriculationNumberContainsKeywordsPredicate firstPredicate =
                new MatriculationNumberContainsKeywordsPredicate(MATRIC_NUMBER_FOURTH_STUDENT);
        MatriculationNumberContainsKeywordsPredicate secondPredicate =
                new MatriculationNumberContainsKeywordsPredicate(MATRIC_NUMBER_FIFTH_STUDENT);


        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        MatriculationNumberContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code MatriculationNumberContainsKeywordsPredicate}.
     */
    private MatriculationNumberContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MatriculationNumberContainsKeywordsPredicate(userInput);
    }
}
