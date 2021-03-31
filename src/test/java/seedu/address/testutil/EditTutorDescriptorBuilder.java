package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.tutorcommands.EditCommand.EditTutorDescriptor;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Notes;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditTutorDescriptor objects.
 */
public class EditTutorDescriptorBuilder {

    private EditTutorDescriptor descriptor;

    public EditTutorDescriptorBuilder() {
        descriptor = new EditTutorDescriptor();
    }

    public EditTutorDescriptorBuilder(EditTutorDescriptor descriptor) {
        this.descriptor = new EditTutorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTutorDescriptorBuilder} with fields containing {@code tutor}'s details
     */
    public EditTutorDescriptorBuilder(Tutor tutor) {
        descriptor = new EditTutorDescriptor();
        descriptor.setName(tutor.getName());
        descriptor.setGender(tutor.getGender());
        descriptor.setPhone(tutor.getPhone());
        descriptor.setEmail(tutor.getEmail());
        descriptor.setAddress(tutor.getAddress());
        descriptor.setNotes(tutor.getNotes());
        descriptor.setSubjectList(tutor.getSubjectList());
        descriptor.setTags(tutor.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withNotes(String notes) {
        descriptor.setNotes(new Notes(notes));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTutorDescriptor}
     * that we are building.
     */
    public EditTutorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code SubjectList} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withSubjectList(
            String subjectName,
            String subjectLevel,
            String subjectRate,
            String subjectExperience,
            String subjectQualification) {
        SubjectList subjects = new SubjectList();
        subjects.add(SampleDataUtil.getSampleTutorSubject(
                subjectName,
                subjectLevel,
                subjectRate,
                subjectExperience,
                subjectQualification));
        descriptor.setSubjectList(subjects);
        return this;
    }

    public EditTutorDescriptor build() {
        return descriptor;
    }
}
