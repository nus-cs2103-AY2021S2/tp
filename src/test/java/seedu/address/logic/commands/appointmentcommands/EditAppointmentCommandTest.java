package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_APPOINTMENT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;


public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalTutorBook(), new UserPrefs(), getTypicalAppointmentBook(),
            getTypicalBudgetBook(), getTypicalGradeBook(), getTypicalScheduleTracker(), getTypicalReminderTracker());

    @Test
    public void execute_editedAppointmentAlreadyExists_failure() {
        List<Appointment> appointmentList = getTypicalAppointments();
        Appointment appointment1 = appointmentList.get(0);
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        editAppointmentDescriptor.setName(appointment1.getName());
        editAppointmentDescriptor.setSubjectName(appointment1.getSubject());
        editAppointmentDescriptor.setTimeFrom(appointment1.getTimeFrom());
        editAppointmentDescriptor.setTimeTo(appointment1.getTimeTo());
        editAppointmentDescriptor.setAddress(appointment1.getLocation());

        EditAppointmentCommand editAppointmentCommand =
                new EditAppointmentCommand(Index.fromOneBased(1), editAppointmentDescriptor);
        assertCommandFailure(editAppointmentCommand, model, MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidIndexOutOfBounds_failure() {
        List<Appointment> appointmentList = getTypicalAppointments();
        Appointment appointment1 = appointmentList.get(0);
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        editAppointmentDescriptor.setName(appointment1.getName());
        editAppointmentDescriptor.setSubjectName(appointment1.getSubject());
        editAppointmentDescriptor.setTimeFrom(appointment1.getTimeFrom());
        editAppointmentDescriptor.setTimeTo(appointment1.getTimeTo());
        editAppointmentDescriptor.setAddress(appointment1.getLocation());

        EditAppointmentCommand editAppointmentCommand =
                new EditAppointmentCommand(Index.fromOneBased(1000000),
                        editAppointmentDescriptor);
        assertCommandFailure(editAppointmentCommand, model,
                MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

    }


}
