package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_EDIT_COMMAND_ERROR;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_CLASH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_END_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LONG_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SHORT_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_MINUTES;
import static seedu.address.commons.core.Messages.MESSAGE_TIME_FROM_GREATER_THAN;
import static seedu.address.commons.util.DateTimeValidationUtil.validateDateTime;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.TypicalModel;

public class DateTimeValidationUtilTest {

    private Model model = TypicalModel.getTypicalModel();

    @Test
    public void validateDateTimeTest() throws CommandException {
        // no event passed in
        assertThrows(CommandException.class, MESSAGE_ADD_EDIT_COMMAND_ERROR, ()
            -> validateDateTime(model));

        // time to before time from
        Appointment appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 5:00 PM")
                .withTimeTo("2021-06-23 1:00 PM").build();
        Appointment finalAppointment = appointment;
        assertThrows(CommandException.class, MESSAGE_TIME_FROM_GREATER_THAN, ()
            -> validateDateTime(model, finalAppointment));

        // past time from
        appointment = new AppointmentBuilder().withTimeFrom("2021-01-23 5:00 PM")
                .withTimeTo("2021-06-21 6:00 PM").build();
        Appointment finalAppointment1 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_DATE, ()
            -> validateDateTime(model, finalAppointment1));

        // past time to
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 5:00 PM")
                .withTimeTo("2021-01-21 6:00 PM").build();
        Appointment finalAppointment2 = appointment;
        assertThrows(CommandException.class, MESSAGE_TIME_FROM_GREATER_THAN, ()
            -> validateDateTime(model, finalAppointment2));

        // time from before 6am
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 5:00 AM")
                .withTimeTo("2021-06-23 8:00 AM").build();
        Appointment finalAppointment3 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_START_TIME, ()
            -> validateDateTime(model, finalAppointment3));

        // time to after 11pm
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 8:00 PM")
                .withTimeTo("2021-06-23 11:30 PM").build();
        Appointment finalAppointment4 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_END_TIME, ()
            -> validateDateTime(model, finalAppointment4));

        // less than 1 hour
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 8:00 PM")
                .withTimeTo("2021-06-23 8:30 PM").build();
        Appointment finalAppointment5 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_SHORT_HOURS, ()
            -> validateDateTime(model, finalAppointment5));

        // more than 8 hours
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 8:00 AM")
                .withTimeTo("2021-06-23 8:30 PM").build();
        Appointment finalAppointment6 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_LONG_HOURS, ()
            -> validateDateTime(model, finalAppointment6));

        // not 30 min or 60 min block time from
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 8:31 AM")
                .withTimeTo("2021-06-23 11:00 AM").build();
        Appointment finalAppointment7 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_TIME_MINUTES, ()
            -> validateDateTime(model, finalAppointment7));

        // not 30 min or 60 min block time to
        appointment = new AppointmentBuilder().withTimeFrom("2021-06-23 8:30 AM")
                .withTimeTo("2021-06-23 11:05 AM").build();
        Appointment finalAppointment8 = appointment;
        assertThrows(CommandException.class, MESSAGE_INVALID_TIME_MINUTES, ()
            -> validateDateTime(model, finalAppointment8));

        // clash
        appointment = new AppointmentBuilder().withTimeFrom("2021-05-24 9:00 AM")
                .withTimeTo("2021-05-24 11:00 AM").build();
        Appointment finalAppointment9 = appointment;
        assertThrows(CommandException.class, MESSAGE_DATE_CLASH, ()
            -> validateDateTime(model, finalAppointment9));

        // clash
        appointment = new AppointmentBuilder().withTimeFrom("2021-05-24 9:00 AM")
                .withTimeTo("2021-05-24 1:00 PM").build();
        Appointment finalAppointment10 = appointment;
        assertThrows(CommandException.class, MESSAGE_DATE_CLASH, ()
            -> validateDateTime(model, finalAppointment10));

        Appointment appointmentInList = model.getFilteredAppointmentList().get(0);
        appointment = new AppointmentBuilder(appointmentInList).withTimeFrom("2021-07-24 9:00 AM")
                .withTimeTo("2021-07-24 1:00 PM").build();
        assertTrue(validateDateTime(model, appointment, appointmentInList));
    }
}
