package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_APPOINTMENT;
import static seedu.address.commons.core.Messages.MESSAGE_TUTOR_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;


public class AddAppointmentCommandTest {

    private final Model model = new ModelManager(getTypicalTutorBook(),
            new UserPrefs(), getTypicalAppointmentBook(), getTypicalBudgetBook(),
            getTypicalGradeBook(), getTypicalScheduleTracker(), getTypicalReminderTracker());


    @Test
    public void execute_noMatchingTutorFound() {
        Appointment firstAppointment =
                model.getAppointmentBook().getAppointmentList().get(0);
        Appointment invalidAppointment =
                new AppointmentBuilder(firstAppointment).withName("Wrong Name").build();

        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(invalidAppointment);

        assertCommandFailure(addAppointmentCommand, model, MESSAGE_TUTOR_DOES_NOT_EXIST);

    }

    @Test
    public void execute_alreadyHasAppointment() {
        Appointment firstAppointment =
                model.getAppointmentBook().getAppointmentList().get(0);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(firstAppointment);

        assertCommandFailure(addAppointmentCommand, model, MESSAGE_DUPLICATE_APPOINTMENT);

    }


}
