package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.commons.core.Messages.MESSAGE_NONEXISTENT_MATRIC_NUM;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIFTH_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void equals() {

        MatriculationNumber firstMatriculationNumber = new MatriculationNumber(MATRIC_NUMBER_FOURTH_STUDENT);
        MatriculationNumber secondMatriculationNumber = new MatriculationNumber(MATRIC_NUMBER_FIFTH_STUDENT);

        StudentContainsMatriculationNumberPredicate firstStudentPredicate =
                new StudentContainsMatriculationNumberPredicate(firstMatriculationNumber);

        StudentContainsMatriculationNumberPredicate secondStudentPredicate =
                new StudentContainsMatriculationNumberPredicate(secondMatriculationNumber);

        AppointmentContainsMatriculationNumberPredicate firstAppointmentPredicate =
                new AppointmentContainsMatriculationNumberPredicate(firstMatriculationNumber);

        AppointmentContainsMatriculationNumberPredicate secondAppointmentPredicate =
                new AppointmentContainsMatriculationNumberPredicate(secondMatriculationNumber);

        FindCommand findFirstCommand = new FindCommand(firstStudentPredicate, firstAppointmentPredicate);
        FindCommand findSecondCommand = new FindCommand(secondStudentPredicate, secondAppointmentPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstStudentPredicate, firstAppointmentPredicate);
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
        String expectedMessage = String.format(MESSAGE_NONEXISTENT_MATRIC_NUM, 0);
        StudentContainsMatriculationNumberPredicate studentPredicate = prepareStudentPredicate("A0876534R");
        AppointmentContainsMatriculationNumberPredicate appointmentPredicate = prepareAppointmentPredicate("A0876534R");
        FindCommand command = new FindCommand(studentPredicate, appointmentPredicate);
        expectedModel.updateFilteredStudentList(studentPredicate);
        expectedModel.updateFilteredAppointmentList(appointmentPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentContainsMatriculationNumberPredicate}.
     */
    private StudentContainsMatriculationNumberPredicate prepareStudentPredicate(String userInput) {
        return new StudentContainsMatriculationNumberPredicate(new MatriculationNumber(userInput));
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsMatriculationNumberPredicate}.
     */
    private AppointmentContainsMatriculationNumberPredicate prepareAppointmentPredicate(String userInput) {
        return new AppointmentContainsMatriculationNumberPredicate(new MatriculationNumber(userInput));
    }
}
