package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(MEET_ALEX.isSameAppointment(MEET_ALEX));

        // null -> returns false
        assertFalse(MEET_ALEX.isSameAppointment(null));

        // same name and date, all other attributes different -> returns true
        Appointment editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withRemark("A different remark").build();
        assertTrue(MEET_ALEX.isSameAppointment(editedMeetAlex));

        // different name, all other attributes same -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withName("Meet Alex on another day").build();
        assertFalse(MEET_ALEX.isSameAppointment(editedMeetAlex));

        // different date, all other attributes same -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withDate(LocalDate.parse("2021-12-31")).build();
        assertFalse(MEET_ALEX.isSameAppointment(editedMeetAlex));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "Meet Alex" + " ";
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withName(nameWithTrailingSpaces).build();
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

        // different person -> returns false
        assertFalse(MEET_ALEX.equals(MEET_BOB));

        // different name -> returns false
        Appointment editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withName("Meet Alex on another day").build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));

        // different remark -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withRemark("A different remark").build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));

        // different date -> returns false
        editedMeetAlex = new AppointmentBuilder(MEET_ALEX).withDate(LocalDate.parse("2021-12-31")).build();
        assertFalse(MEET_ALEX.equals(editedMeetAlex));
    }
}
