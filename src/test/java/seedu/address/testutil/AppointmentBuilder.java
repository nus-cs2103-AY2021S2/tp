package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Address;
import seedu.address.model.Name;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Parent teacher meeting";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE = LocalDateTime.now().plusDays(4).format(DateTime.DATE_TIME_FORMATTER);
    // public static final String DEFAULT_CONTACTS = "1";

    private Name name;
    private Address address;
    private DateTime dateTime;
    private Set<Contact> contacts;
    private Set<Tag> tags;

    /**
     * Creates an {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        dateTime = new DateTime(DEFAULT_DATE);
        contacts = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        address = appointmentToCopy.getAddress();
        dateTime = appointmentToCopy.getDateTime();
        contacts = new HashSet<>(appointmentToCopy.getContacts());
        tags = new HashSet<>(appointmentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code Appointment} that
     * we are building.
     */
    public AppointmentBuilder withContacts(String ... contacts) {
        this.contacts = SampleDataUtil.getContactSet(contacts);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Contact} that we are building.
     */
    public AppointmentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getChildTagSet(tags);
        return this;
    }

    public Appointment build() {
        return new Appointment(name, address, dateTime, contacts, tags);
    }

}
