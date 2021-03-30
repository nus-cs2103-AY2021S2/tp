package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.StudentBook;
import seedu.student.model.UserPrefs;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.testutil.AppointmentBuilder;
import seedu.student.testutil.EditAppointmentDescriptorBuilder;
import seedu.student.testutil.TypicalAppointments;
import seedu.student.testutil.TypicalMatricNumbers;

class EditAppointmentCommandTest {
    private Model model = new ModelManager(TypicalAppointments.getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Appointment editedAppointment = new AppointmentBuilder().buildAlice();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(
                new MatriculationNumber(TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT), descriptor);
        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new StudentBook(model.getStudentBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0).getFilteredAppointmentList().get(0),
                editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(
                new MatriculationNumber(TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT),
                new EditAppointmentDescriptorBuilder(new AppointmentBuilder().buildAlice()).build());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(0).getFilteredAppointmentList().get(0);
        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new StudentBook(model.getStudentBook()), new UserPrefs());

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment firstAppointment = model.getFilteredAppointmentList().get(0).getFilteredAppointmentList().get(0);
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(firstAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(
                new MatriculationNumber(TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT), descriptor);
        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_duplicateAppointmentFilteredList_failure() {
        // edit student in filtered list into a duplicate in address book
        Appointment appointmentInList = model.getFilteredAppointmentList().get(0).getFilteredAppointmentList().get(0);
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(
                new MatriculationNumber(TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT),
                new EditAppointmentDescriptorBuilder(appointmentInList).build());
        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void equals() {
        Appointment editedAppointment = new AppointmentBuilder().build();
        EditAppointmentCommand editApptFirstCommand = new EditAppointmentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT), new EditAppointmentDescriptorBuilder(editedAppointment).build());
        EditAppointmentCommand editApptSecondCommand = new EditAppointmentCommand(new MatriculationNumber(
                MATRIC_NUMBER_SECOND_STUDENT), new EditAppointmentDescriptorBuilder(editedAppointment).build());

        // same object -> returns true
        assertTrue(editApptFirstCommand.equals(editApptFirstCommand));

        // same values -> returns true
        EditAppointmentCommand editApptFirstCommandCopy = new EditAppointmentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT), new EditAppointmentDescriptorBuilder(editedAppointment).build());
        assertTrue(editApptFirstCommand.equals(editApptFirstCommandCopy));

        // different types -> returns false
        assertFalse(editApptFirstCommand.equals(1));

        // null -> returns false
        assertFalse(editApptFirstCommand.equals(null));
    }

}
