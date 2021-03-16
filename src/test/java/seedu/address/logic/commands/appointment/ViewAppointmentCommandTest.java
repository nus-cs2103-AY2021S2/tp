package seedu.address.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtDate;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_INVALID_DATE;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_SECOND_DATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.appointmentcommands.ViewAppointmentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.person.Person;

public class ViewAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAppointmentBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAppointmentBook());

    @Test
    public void execute_validDateUnfilteredList_success() {
        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        Appointment appointmentToView = model.getFilteredAppointmentList().filtered(predicate).get(0);
        ViewAppointmentCommand viewCommand = new ViewAppointmentCommand(predicate);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                appointmentToView.getDateTime().toDateString());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateFilteredList_success() {
        showAppointmentAtDate(model, APPOINTMENT_FIRST_DATE);

        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        Appointment appointmentToView = model.getFilteredAppointmentList().filtered(predicate).get(0);
        ViewAppointmentCommand viewCommand = new ViewAppointmentCommand(predicate);

        String expectedMessage = String.format(ViewAppointmentCommand.MESSAGE_VIEW_APPOINTMENT_SUCCESS,
                appointmentToView.getDateTime().toDateString());
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
