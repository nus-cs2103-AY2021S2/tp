package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LOCALDATE_MEET_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_LOCALTIME_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MEET_LOCALTIME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditAppointmentCommand;
import seedu.address.logic.commands.edit.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
        Appointment editedAppointment =
                new AppointmentBuilder().withName("Meet alice")
                                        .withDate(VALID_DATE_LOCALDATE_MEET_ALICE)
                                        .withRemark("again")
                                        .withTime(VALID_TIME_MEET_LOCALTIME_ALICE)
                                        .build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
        Index indexLastAppointment = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastAppointment.getZeroBased());

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment);
        Appointment editedAppointment = appointmentInList.withName("Meet someone called alex")
                                                         .withRemark("second time")
                                                         .withTime(VALID_TIME_LOCALTIME_MEET_ALEX)
                                                         .build();

        EditAppointmentDescriptor descriptor =
            new EditAppointmentDescriptorBuilder(editedAppointment).build();

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }
}
