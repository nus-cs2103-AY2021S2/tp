package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment editedAppointment = new AppointmentBuilder().build();
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editApptCommand = new EditAppointmentCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editApptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment firstAppt = model.getFilteredAppointmentList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(firstAppt).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_SECOND_CONTACT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, MESSAGE_DUPLICATE_APPOINTMENT);
    }
}
