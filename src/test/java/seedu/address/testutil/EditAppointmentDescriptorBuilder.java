package seedu.address.testutil;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;
/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        // set to index 1 for both patient and doctor idex
        descriptor.setPatientIndex(INDEX_FIRST_IN_LIST);
        descriptor.setDoctorIndex(INDEX_FIRST_IN_LIST);
        descriptor.setStart(appointment.getAppointmentStart());
        descriptor.setEnd(appointment.getAppointmentEnd());
    }

    /**
     * Sets the {@code patientIndex} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPatientIndex(int patientIndex) {
        descriptor.setPatientIndex(Index.fromOneBased(patientIndex));
        return this;
    }

    /**
     * Sets the {@code doctorIndex} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDoctorIndex(int doctorIndex) {
        descriptor.setDoctorIndex(Index.fromOneBased(doctorIndex));
        return this;
    }

    /**
     * Sets the {@code start} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStart(String start) {
        descriptor.setStart(LocalDateTime.parse(start));
        return this;
    }

    /**
     * Sets the {@code emd} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withAddress(String end) {
        descriptor.setEnd(LocalDateTime.parse(end));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditAppointmentDescriptor}
     * that we are building.
     */
    public EditAppointmentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
