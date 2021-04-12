package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.model.Address;
import seedu.address.model.Name;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.ChildTag;
import seedu.address.model.tag.Tag;



/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {
    private EditAppointmentCommand.EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentCommand.EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code contact}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setName(appointment.getName());
        descriptor.setAddress(appointment.getAddress());
        descriptor.setDateTime(appointment.getDateTime());
        descriptor.setContacts(appointment.getContacts());
        descriptor.setTags(appointment.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new DateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditAppointmentDescriptor}
     * that we are building.
     */
    public EditAppointmentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(ChildTag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code EditAppointmentDescriptor}
     * that we are building.
     */
    public EditAppointmentDescriptorBuilder withContacts(Contact... contacts) {
        Set<Contact> contactSet = Stream.of(contacts).collect(Collectors.toSet());
        descriptor.setContacts(contactSet);
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}
