package seedu.address.testutil;

import seedu.address.model.appointment.AppointmentDateTime;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalDates {
    public static final AppointmentDateTime APPOINTMENT_FIRST_DATE = new AppointmentDateTime("2021-03-24");
    public static final AppointmentDateTime APPOINTMENT_SECOND_DATE = new AppointmentDateTime("2021-03-27");
    public static final AppointmentDateTime APPOINTMENT_THIRD_DATE = new AppointmentDateTime("2021-03-28");

    public static final AppointmentDateTime APPOINTMENT_INVALID_DATE = new AppointmentDateTime("1999-02-31");

}
