package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LOCALDATE_MEET_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_LOCALTIME_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MEET_LOCALTIME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.TypicalModelManager;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Model model = TypicalModelManager.getTypicalModelManager();
        Appointment editedAppointment =
                new AppointmentBuilder().withName("Meet alice")
                                        .withDate(VALID_DATE_LOCALDATE_MEET_ALICE)
                                        .withRemark("again")
                                        .withTime(VALID_TIME_MEET_LOCALTIME_ALICE)
                                        .build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_SUCCESS, editedAppointment);

        Model expectedModel = TypicalModelManager.getTypicalModelManager();

        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Model model = TypicalModelManager.getTypicalModelManager();
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

        Model expectedModel = TypicalModelManager.getTypicalModelManager();
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }
}
