package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertAppointmentCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddAppointmentCommand}.
 */
public class AddAppointmentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
    }

    @Test
    public void execute_newAppointment_success() {
        Appointment validAppointment = new AppointmentBuilder().build();

        Model expectedModel = new ModelManager(model.getAppointmentBook(), new UserPrefs());
        expectedModel.addAppointment(validAppointment);

        assertCommandSuccess(new AddAppointmentCommand(validAppointment), model,
                String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment), expectedModel);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment appointmentInList = model.getAppointmentBook().getAppointmentList().get(0);
        assertAppointmentCommandFailure(new AddAppointmentCommand(appointmentInList), model,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

}
