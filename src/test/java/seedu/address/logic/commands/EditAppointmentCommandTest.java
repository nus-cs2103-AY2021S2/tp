package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_LOCALTIME_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.DateTimeFormat;
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
        Appointment editedAppointment = new AppointmentBuilder().withName("Meet alice")
                .withRemark("again")
                .withDate(LocalDate.parse("25-09-2021", DateTimeFormat.INPUT_DATE_FORMAT))
                .withTime(LocalTime.parse("2200", DateTimeFormat.INPUT_TIME_FORMAT))
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
