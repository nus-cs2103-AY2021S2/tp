package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Meet Alex";
    public static final String DEFAULT_REMARK = "At M Hotel";
    public static final LocalDate DEFAULT_DATE = LocalDate.parse("2021-12-25");

    private Name name;
    private Remark remark;
    private Date date;

    /**
     * Creates an {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        remark = new Remark(DEFAULT_REMARK);
        date = new Date(DEFAULT_DATE);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        remark = appointmentToCopy.getRemarks();
        date = appointmentToCopy.getDate();
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

    public Appointment build() {
        return new Appointment(name, remark, date);
    }
}
