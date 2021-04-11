package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.DateTimeFormat;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment MEET_ALEX = new AppointmentBuilder().withName("Meet Alex")
            .withRemark("Bring him around Bishan to look at the properties")
            .withDate(LocalDate.parse("25-12-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1500", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();
    public static final Appointment MEET_BOB = new AppointmentBuilder().withName("Meet Bob")
            .withRemark("To meet with interested client for viewing of his house")
            .withDate(LocalDate.parse("30-04-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1030", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();
    public static final Appointment MEET_CALEB = new AppointmentBuilder().withName("Meet Caleb")
            .withRemark("For renegotiation of selling price")
            .withDate(LocalDate.parse("07-03-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1030", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();
    public static final Appointment MEET_DARREN = new AppointmentBuilder().withName("Meet Darren")
            .withRemark("For signing of sales agreement at HDB")
            .withDate(LocalDate.parse("12-10-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1400", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();
    public static final Appointment MEET_EMILY = new AppointmentBuilder().withName("Meet Emily")
            .withRemark("For initial evaluation of her house")
            .withDate(LocalDate.parse("15-06-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1100", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();

    // Manually added
    public static final Appointment MEET_FIN = new AppointmentBuilder().withName("Meet Fin")
            .withRemark("At Ritz Carlton")
            .withDate(LocalDate.parse("16-09-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1300", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();
    public static final Appointment MEET_JOEL = new AppointmentBuilder().withName("Meet Joel")
            .withRemark("At Sentosa Cove")
            .withDate(LocalDate.parse("19-10-2021", DateTimeFormat.INPUT_DATE_FORMAT))
            .withTime(LocalTime.parse("1000", DateTimeFormat.INPUT_TIME_FORMAT))
            .build();

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
        return new ArrayList<>(Arrays.asList(MEET_ALEX, MEET_BOB, MEET_CALEB, MEET_DARREN, MEET_EMILY));
    }
}
