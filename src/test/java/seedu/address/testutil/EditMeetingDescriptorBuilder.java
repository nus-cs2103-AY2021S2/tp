package seedu.address.testutil;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.meetings.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;

public class EditMeetingDescriptorBuilder {
    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setName(meeting.getName());
        descriptor.setStart(meeting.getStart());
        descriptor.setTerminate(meeting.getTerminate());
        descriptor.setPriority(meeting.getPriority());
        descriptor.setDescription(meeting.getDescription());
        descriptor.setGroups(meeting.getGroups());
    }

    /**
     * Sets the {@code MeetingName} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withName(String name) {
        descriptor.setName(new MeetingName(name));
        return this;
    }

    /**
     * Sets the {@code Start} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withStart(String start) {
        descriptor.setStart(new DateTime(start));
        return this;
    }

    /**
     * Sets the {@code Terminate} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withTerminate(String terminate) {
        descriptor.setTerminate(new DateTime(terminate));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code EditMeetingDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withGroups(String... groups) {
        Set<Group> groupSet = Stream.of(groups).map(Group::new).collect(Collectors.toSet());
        descriptor.setGroups(groupSet);
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }

}
