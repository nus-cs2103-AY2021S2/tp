package seedu.address.testutil;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.subject.Subject;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setSchool(person.getSchool());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setGuardianName(person.getGuardianName());
        descriptor.setGuardianPhone(person.getGuardianPhone());
        descriptor.setLevel(person.getLevel());
        descriptor.setSubjects(person.getSubjects());
        descriptor.setLessons(person.getLessons());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code School} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSchool(String school) {
        descriptor.setSchool(Optional.of(new School(school)));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(Optional.of(new Email(email)));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(Optional.of(new Address(address)));
        return this;
    }

    /**
     * Sets the {@code GuardianName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGuardianName(String name) {
        descriptor.setGuardianName(Optional.of(new Name(name)));
        return this;
    }

    /**
     * Sets the {@code GuardianPhone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGuardianPhone(String phone) {
        descriptor.setGuardianPhone(Optional.of(new Phone(phone)));
        return this;
    }

    /**
     * Parses the {@code subjects} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withSubjects(String... subjects) {
        Set<Subject> subjectSet = Stream.of(subjects).map(Subject::new).collect(Collectors.toSet());
        descriptor.setSubjects(subjectSet);
        return this;
    }

    /**
     * Parses the {@code lessons} into a {@code Set<Lesson>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withLessons(String... lessons) {
        Set<Lesson> lessonSet = Stream.of(lessons).map(Lesson::new).collect(Collectors.toSet());
        descriptor.setLessons(lessonSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
