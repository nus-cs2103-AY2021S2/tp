package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

public class TypicalAppointments {
    public static final Appointment PTM = new AppointmentBuilder().withName("PTM")
            .withAddress("Child 1's school").withDateTime("09/01/2021 00:00")
            .withContacts("2").build();
    public static final Appointment PLAY_DATE = new AppointmentBuilder().withName("Play date with xyz")
            .withAddress("xyz's house").withDateTime("30/03/2021 10:00")
            .withContacts("1", "2").build();
    public static final Appointment PSG_MEETING = new AppointmentBuilder().withName("PSG meeting")
            .withAddress("child 3's school").withDateTime("15/04/2021 14:00")
            .withContacts("3", "4", "1").build();
    public static final Appointment BALLET_RECITAL = new AppointmentBuilder().withName("ballet recital")
            .withAddress("child 1's school").withDateTime("23/12/2021 18:00").build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AppointmentBook} with all the typical appointments.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(PLAY_DATE, BALLET_RECITAL));
    }
}
