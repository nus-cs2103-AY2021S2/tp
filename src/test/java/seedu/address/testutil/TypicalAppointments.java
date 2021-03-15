package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment MEET_ALEX = new AppointmentBuilder().withName("Meet Alex")
            .withRemark("At M Hotel").withDate(LocalDate.parse("2021-12-25"))
            .withTime(LocalTime.parse("15:00")).build();
    public static final Appointment MEET_BOB = new AppointmentBuilder().withName("Meet Bob")
            .withRemark("At Plaza Sing Starbucks").withDate(LocalDate.parse("2021-02-25"))
            .withTime(LocalTime.parse("20:00")).build();
    public static final Appointment MEET_CHARLIE = new AppointmentBuilder().withName("Meet Charlie")
            .withRemark("At client's house").withDate(LocalDate.parse("2021-08-17"))
            .withTime(LocalTime.parse("12:30")).build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AppointmentBook} with all the typical appointments.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook appointmentBook = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            appointmentBook.addAppointment(appointment);
        }
        return appointmentBook;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(MEET_ALEX, MEET_BOB, MEET_CHARLIE));
    }
}
