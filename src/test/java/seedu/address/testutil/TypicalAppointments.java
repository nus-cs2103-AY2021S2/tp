package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment MATHS_APPOINTMENT = new AppointmentBuilder()
            .withEmail("alexyeoh@example.com")
            .withSubject("Mathematics").withDateTime("2020-02-24 14:00")
            .withAddress("Geylang").build();
    public static final Appointment SCIENCE_APPOINTMENT = new AppointmentBuilder()
            .withEmail("bernice@example.com")
            .withSubject("Science").withDateTime("2020-02-27 15:00")
            .withAddress("Hougang").build();

    public static final String KEYWORD_MATCHING_ALEX = "Alex"; // A keyword that matches ALEX

    private TypicalAppointments() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(MATHS_APPOINTMENT, SCIENCE_APPOINTMENT));
    }
}
