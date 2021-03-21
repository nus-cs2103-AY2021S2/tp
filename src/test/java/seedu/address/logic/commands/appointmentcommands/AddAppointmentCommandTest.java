package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_SECOND_DATE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateViewPredicate;


public class AddAppointmentCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAppointmentBook());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalAppointmentBook());


    @Test
    public void execute_validAppointment_success() {
        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_SECOND_DATE);
        Appointment appointmentToTest =
                model.getFilteredAppointmentList().filtered(predicate).get(0);
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(appointmentToTest);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                appointmentToTest.getTimeFrom().toDateString());

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage,
                expectedModel);

    }

}
