package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
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
        .withRemark("At M Hotel")
        .withDate(LocalDate.parse("25-12-2021",
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT)))
        .withTime(LocalTime.parse("1500", DateTimeFormatter.ofPattern("HHmm"))).build();
    public static final Appointment MEET_BOB = new AppointmentBuilder().withName("Meet Bob")
        .withRemark("At Plaza Sing Starbucks")
        .withDate(LocalDate.parse("25-02-2021",
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT)))
        .withTime(LocalTime.parse("2000", DateTimeFormatter.ofPattern("HHmm"))).build();
    public static final Appointment MEET_CHARLIE = new AppointmentBuilder().withName("Meet Charlie")
        .withRemark("At client's house")
        .withDate(LocalDate.parse("17-08-2021",
            DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT)))
        .withTime(LocalTime.parse("1230", DateTimeFormatter.ofPattern("HHmm"))).build();
    public static final Appointment MEET_DARREN = new AppointmentBuilder().withName("Meet Darren")
            .withRemark("At MBS Starbucks")
            .withDate(LocalDate.parse("12-10-2021",
                    DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT)))
            .withTime(LocalTime.parse("1400", DateTimeFormatter.ofPattern("HHmm"))).build();
    public static final Appointment MEET_EMILY = new AppointmentBuilder().withName("Meet Emily")
            .withRemark("At MayFair Gardens")
            .withDate(LocalDate.parse("15-06-2021",
                    DateTimeFormatter.ofPattern("d-M-u").withResolverStyle(ResolverStyle.STRICT)))
            .withTime(LocalTime.parse("1100", DateTimeFormatter.ofPattern("HHmm"))).build();

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
        return new ArrayList<>(Arrays.asList(MEET_ALEX, MEET_BOB, MEET_CHARLIE, MEET_DARREN, MEET_EMILY));
    }
}
