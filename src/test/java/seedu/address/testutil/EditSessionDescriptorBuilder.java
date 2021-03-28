package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditSessionCommand;
import seedu.address.model.session.Day;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.Subject;
import seedu.address.model.session.Timeslot;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditSessionDescriptor objects.
 */
public class EditSessionDescriptorBuilder {
    private EditSessionCommand.EditSessionDescriptor descriptor;

    public EditSessionDescriptorBuilder() {
        descriptor = new EditSessionCommand.EditSessionDescriptor();
    }

    public EditSessionDescriptorBuilder(EditSessionCommand.EditSessionDescriptor descriptor) {
        this.descriptor = new EditSessionCommand.EditSessionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSessionDescriptor} with fields containing {@code session}'s details
     */
    public EditSessionDescriptorBuilder(Session session) {
        descriptor = new EditSessionCommand.EditSessionDescriptor();
        descriptor.setSessionId(session.getClassId());
        descriptor.setDay(session.getDay());
        descriptor.setSubject(session.getSubject());
        descriptor.setTimeSlot(session.getTimeslot());
        descriptor.setTags(session.getTags());
    }

    /**
     * Sets the {@code Session Id} of the {@code EditSessionDescriptor} that we are building.
     */
    public seedu.address.testutil.EditSessionDescriptorBuilder withSessionId(String sessionId) {
        descriptor.setSessionId(new SessionId(sessionId));
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code EditSessionDescriptor} that we are building.
     */
    public seedu.address.testutil.EditSessionDescriptorBuilder withDay(String day) {
        descriptor.setDay(new Day(day));
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code EditSessionDescriptor} that we are building.
     */
    public seedu.address.testutil.EditSessionDescriptorBuilder withSubject(String subject) {
        descriptor.setSubject(new Subject(subject));
        return this;
    }

    /**
     * Sets the {@code Timeslot} of the {@code EditSessionDescriptor} that we are building.
     */
    public seedu.address.testutil.EditSessionDescriptorBuilder withTimeslot(String timeslot) {
        descriptor.setTimeSlot(new Timeslot(timeslot));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSessionDescriptor}
     * that we are building.
     */
    public seedu.address.testutil.EditSessionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditSessionCommand.EditSessionDescriptor build() {
        return descriptor;
    }
}
