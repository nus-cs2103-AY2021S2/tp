package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LOCALDATE_MEET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_MEET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_LOCALTIME_MEET_BOB;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;
import static seedu.address.testutil.TypicalAppointments.MEET_CALEB;
import static seedu.address.testutil.TypicalAppointments.MEET_DARREN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(MEET_ALEX.isSameAppointment(MEET_ALEX));

        // null -> returns false
        assertFalse(MEET_ALEX.isSameAppointment(null));

        // same date and time, all other attributes different -> returns true
        Appointment editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withName(VALID_NAME_MEET_BOB)
            .withRemark(VALID_REMARK_MEET_BOB).build();
        assertTrue(MEET_ALEX.isSameAppointment(editedMeetAlex));

        // different date, all other attributes same -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withDate(VALID_DATE_LOCALDATE_MEET_BOB).build();
        assertFalse(MEET_ALEX.isSameAppointment(editedMeetAlex));

        // different time, all other attributes same -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withTime(VALID_TIME_LOCALTIME_MEET_BOB).build();
        assertFalse(MEET_ALEX.isSameAppointment(editedMeetAlex));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment meetAlexCopy = new AppointmentBuilder(MEET_ALEX).build();
        assertTrue(MEET_ALEX.equals(meetAlexCopy));

        // same object -> returns true
        assertTrue(MEET_ALEX.equals(MEET_ALEX));

        // null -> returns false
        assertFalse(MEET_ALEX.equals(null));

        // different type -> returns false
        assertFalse(MEET_ALEX.equals(5));

        // different appointment -> returns false
        assertFalse(MEET_ALEX.equals(MEET_BOB));

        // different name -> returns false
        Appointment editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withName(VALID_NAME_MEET_BOB).build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));

        // different remark -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withRemark(VALID_REMARK_MEET_BOB).build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));

        // different date -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withDate(VALID_DATE_LOCALDATE_MEET_BOB).build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));

        // different time -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withTime(VALID_TIME_LOCALTIME_MEET_BOB).build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));
    }

    @Test
    public void testStringConversion() {
        assertEquals("Meet Alex; Remarks: Bring him around Bishan to look at the properties; "
                + "Date: Dec 25, 2021; Time: 3:00PM", MEET_ALEX.toString());
        assertEquals("Meet Bob; Remarks: To meet with interested client for viewing of his house; "
                + "Date: Apr 30, 2021; Time: 10:30AM", MEET_BOB.toString());
        assertEquals("Meet Caleb; Remarks: For renegotiation of selling price; "
                + "Date: Mar 07, 2021; Time: 10:30AM", MEET_CALEB.toString());
        assertEquals("Meet Darren; Remarks: For signing of sales agreement at HDB; "
                + "Date: Oct 12, 2021; Time: 2:00PM", MEET_DARREN.toString());
    }
}
