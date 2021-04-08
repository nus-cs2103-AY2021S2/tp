package seedu.iscam.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.iscam.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.CompletionStatus;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;


/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
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
        descriptor.setClientName(meeting.getClientName());
        descriptor.setDateTime(meeting.getDateTime());
        descriptor.setLocation(meeting.getLocation());
        descriptor.setDescription(meeting.getDescription());
        descriptor.setTags(meeting.getTags());
        descriptor.setStatus(meeting.getStatus());
    }

    /**
     * Sets the {@code ClientName} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withClientName(String name) {
        descriptor.setClientName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new DateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditMeetingDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new CompletionStatus(status));
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
