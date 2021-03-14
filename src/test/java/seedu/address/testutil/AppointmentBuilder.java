package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.subject.SubjectName;

/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_SUBJECT = "Mathematics";
    public static final String DEFAULT_DATETIME = "2020-02-24 14:00";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Email email;
    private SubjectName subject;
    private AppointmentDateTime dateTime;
    private Address address;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public AppointmentBuilder() {
        email = new Email(DEFAULT_EMAIL);
        subject = new SubjectName(DEFAULT_SUBJECT);
        dateTime = new AppointmentDateTime(DEFAULT_DATETIME);
        address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        email = appointmentToCopy.getEmail();
        subject = appointmentToCopy.getSubject();
        dateTime = appointmentToCopy.getDateTime();
        address = appointmentToCopy.getLocation();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withSubject(String subject) {
        this.subject = new SubjectName(subject);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public AppointmentBuilder withDateTime(String dateTime) {
        this.dateTime = new AppointmentDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public Appointment build() {
        return new Appointment(email, subject, dateTime, address);
    }

}
