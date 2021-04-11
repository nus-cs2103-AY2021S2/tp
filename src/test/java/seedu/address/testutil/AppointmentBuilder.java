package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;
import seedu.address.model.util.DateTimeFormat;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Meet Jacob";
    public static final String DEFAULT_REMARK = "For collection of commission";
    public static final LocalDate DEFAULT_DATE =
            LocalDate.parse("19-05-2021", DateTimeFormat.INPUT_DATE_FORMAT);
    public static final LocalTime DEFAULT_TIME =
            LocalTime.parse("1930", DateTimeFormat.INPUT_TIME_FORMAT);

    private Name name;
    private Remark remark;
    private Date date;
    private Time time;

    /**
     * Creates an {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        remark = new Remark(DEFAULT_REMARK);
        date = new Date(DEFAULT_DATE);
        time = new Time(DEFAULT_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        remark = appointmentToCopy.getRemarks();
        date = appointmentToCopy.getDate();
        time = appointmentToCopy.getTime();
    }

    /**
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withRemark(String type) {
        this.remark = new Remark(type);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDate(LocalDate date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTime(LocalTime time) {
        this.time = new Time(time);
        return this;
    }

    public Appointment build() {
        return new Appointment(name, remark, date, time);
    }
}
