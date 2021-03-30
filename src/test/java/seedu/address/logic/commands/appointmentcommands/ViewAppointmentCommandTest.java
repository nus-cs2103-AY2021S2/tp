package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtDate;
import static seedu.address.testutil.ModelManagerBuilder.ModelType.APPOINTMENTBOOK;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_SECOND_DATE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.testutil.ModelManagerBuilder;

public class ViewAppointmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = ModelManagerBuilder.getModelManager();
        expectedModel = ModelManagerBuilder.getModelManager(model, APPOINTMENTBOOK);
    }

    @Test
    public void execute_validDateUnfilteredList_success() {
        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        Appointment appointmentToView = model.getFilteredAppointmentList().filtered(predicate).get(0);
        ViewAppointmentCommand viewCommand = new ViewAppointmentCommand(predicate);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                appointmentToView.getTimeFrom().toDateString());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateFilteredList_success() {
        showAppointmentAtDate(model, APPOINTMENT_FIRST_DATE);

        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        Appointment appointmentToView = model.getFilteredAppointmentList().filtered(predicate).get(0);
        ViewAppointmentCommand viewCommand = new ViewAppointmentCommand(predicate);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                appointmentToView.getTimeFrom().toDateString());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DateViewPredicate firstPredicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        DateViewPredicate secondPredicate = new DateViewPredicate(APPOINTMENT_SECOND_DATE);

        ViewAppointmentCommand viewFirstCommand = new ViewAppointmentCommand(firstPredicate);
        ViewAppointmentCommand viewSecondCommand = new ViewAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewAppointmentCommand viewFirstCommandCopy = new ViewAppointmentCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
