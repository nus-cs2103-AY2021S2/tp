package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.Assert.assertThrows;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIFTH_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;
import seedu.student.model.student.exceptions.MatriculationNumberDoesNotExistException;
import seedu.student.testutil.TypicalAppointments;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model modelWithNoAppointments = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    private Model modelWithAppointments = new ModelManager(TypicalAppointments.getTypicalStudentBook(),
            new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    private final Model expectedModelWithAppointment = new ModelManager(TypicalAppointments.getTypicalStudentBook(),
            new UserPrefs());

    @Test
    public void equals() {

        MatriculationNumber firstMatriculationNumber = new MatriculationNumber(MATRIC_NUMBER_FOURTH_STUDENT);
        MatriculationNumber secondMatriculationNumber = new MatriculationNumber(MATRIC_NUMBER_FIFTH_STUDENT);

        StudentContainsMatriculationNumberPredicate firstStudentPredicate =
                new StudentContainsMatriculationNumberPredicate(firstMatriculationNumber);

        StudentContainsMatriculationNumberPredicate secondStudentPredicate =
                new StudentContainsMatriculationNumberPredicate(secondMatriculationNumber);

        AppointmentListContainsMatriculationNumberPredicate firstAppointmentListPredicate =
                new AppointmentListContainsMatriculationNumberPredicate(firstMatriculationNumber);

        AppointmentListContainsMatriculationNumberPredicate secondAppointmentListPredicate =
                new AppointmentListContainsMatriculationNumberPredicate(secondMatriculationNumber);

        AppointmentContainsMatriculationNumberPredicate firstAppointmentPredicate =
                new AppointmentContainsMatriculationNumberPredicate(firstMatriculationNumber);

        AppointmentContainsMatriculationNumberPredicate secondAppointmentPredicate =
                new AppointmentContainsMatriculationNumberPredicate(secondMatriculationNumber);

        FindCommand findFirstCommand = new FindCommand(firstStudentPredicate, firstAppointmentListPredicate,
                firstAppointmentPredicate);
        FindCommand findSecondCommand = new FindCommand(secondStudentPredicate, secondAppointmentListPredicate,
                secondAppointmentPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstStudentPredicate, firstAppointmentListPredicate,
                firstAppointmentPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentNoAppointmentFound() {

        StudentContainsMatriculationNumberPredicate studentPredicate = prepareStudentPredicate("A0876534R");
        AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate =
                prepareAppointmentListPredicate("A0208976R");
        AppointmentContainsMatriculationNumberPredicate appointmentPredicate =
                prepareAppointmentPredicate("A0208976R");
        FindCommand command = new FindCommand(studentPredicate, appointmentListPredicate, appointmentPredicate);

        assertThrows(MatriculationNumberDoesNotExistException.class, String.format(command.MESSAGE_NO_STUDENT_FOUND,
                studentPredicate.getKeyword()), () -> command.execute(modelWithNoAppointments));
    }

    @Test
    public void execute_oneKeyword_oneStudentNoAppointmentFound() {

        StudentContainsMatriculationNumberPredicate studentPredicate = prepareStudentPredicate("A4567890E");
        AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate =
                prepareAppointmentListPredicate("A4567890E");
        AppointmentContainsMatriculationNumberPredicate appointmentPredicate =
                prepareAppointmentPredicate("A4567890E");
        FindCommand command = new FindCommand(studentPredicate, appointmentListPredicate, appointmentPredicate);

        Student student = modelWithNoAppointments.getStudent(new MatriculationNumber("A4567890E"));
        expectedModel.updateFilteredStudentList(studentPredicate);
        expectedModel.updateFilteredAppointmentList(appointmentListPredicate, appointmentPredicate);

        assertCommandSuccess(command, modelWithNoAppointments, command.MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND,
                expectedModel);
        assertEquals(Arrays.asList(student), modelWithNoAppointments.getFilteredStudentList());
    }

    @Test
    public void execute_oneKeyword_oneStudentAndAppointmentFound() {

        StudentContainsMatriculationNumberPredicate studentPredicate = prepareStudentPredicate("A5678901F");
        AppointmentListContainsMatriculationNumberPredicate appointmentListPredicate =
                prepareAppointmentListPredicate("A5678901F");
        AppointmentContainsMatriculationNumberPredicate appointmentPredicate =
                prepareAppointmentPredicate("A5678901F");
        FindCommand command = new FindCommand(studentPredicate, appointmentListPredicate, appointmentPredicate);

        expectedModelWithAppointment.updateFilteredStudentList(studentPredicate);
        expectedModelWithAppointment.updateFilteredAppointmentList(appointmentListPredicate, appointmentPredicate);

        Student student = expectedModelWithAppointment.getStudent(new MatriculationNumber("A5678901F"));
        Appointment appointment = expectedModelWithAppointment.getAppointmentList().get(2);
        assertCommandSuccess(command, modelWithAppointments, command.MESSAGE_STUDENTS_AND_APPOINTMENT_FOUND,
                expectedModelWithAppointment);

        assertEquals(Arrays.asList(student),
                expectedModelWithAppointment.getFilteredStudentList());
        assertEquals(Arrays.asList(appointment),
                expectedModelWithAppointment.getFilteredAppointmentList().get(0).getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentContainsMatriculationNumberPredicate}.
     */
    private StudentContainsMatriculationNumberPredicate prepareStudentPredicate(String userInput) {
        return new StudentContainsMatriculationNumberPredicate(new MatriculationNumber(userInput));
    }

    private AppointmentListContainsMatriculationNumberPredicate prepareAppointmentListPredicate(String userInput) {
        return new AppointmentListContainsMatriculationNumberPredicate(new MatriculationNumber(userInput));
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsMatriculationNumberPredicate}.
     */
    private AppointmentContainsMatriculationNumberPredicate prepareAppointmentPredicate(String userInput) {
        return new AppointmentContainsMatriculationNumberPredicate(new MatriculationNumber(userInput));
    }
}
