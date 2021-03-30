package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.subject.SubjectName;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "John Lim";
    public static final String DEFAULT_SUBJECT = "Mathematics";
    public static final String DEFAULT_TIMEFROM = "2020-02-24 12:00PM";
    public static final String DEFAULT_TIMETO = "2020-02-24 12:30PM";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private SubjectName subject;
    private AppointmentDateTime timeFrom;
    private AppointmentDateTime timeTo;
    private Address address;

    /**
     * Creates an {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        subject = new SubjectName(DEFAULT_SUBJECT);
        timeFrom = new AppointmentDateTime(DEFAULT_TIMEFROM);
        timeTo = new AppointmentDateTime(DEFAULT_TIMETO);
        address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        subject = appointmentToCopy.getSubject();
        timeFrom = appointmentToCopy.getTimeFrom();
        timeTo = appointmentToCopy.getTimeTo();
        address = appointmentToCopy.getLocation();
    }

    /**
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code SubjectName} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withSubject(String subject) {
        this.subject = new SubjectName(subject);
        return this;
    }

    /**
     * Parses the {@code timeFrom} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimeFrom(String dateTime) {
        this.timeFrom = new AppointmentDateTime(dateTime);
        return this;
    }

    /**
     * Parses the {@code timeTo} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimeTo(String dateTime) {
        this.timeTo = new AppointmentDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public Appointment build() {
        return new Appointment(name, subject, timeFrom, timeTo, address);
    }

}
