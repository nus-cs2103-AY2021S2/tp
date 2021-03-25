package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStatus;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setEventName(event.getName());
        descriptor.setDescription(event.getDescription());
        descriptor.setEventStatus(event.getStatus());
        // descriptor.setTags(event.getTags()); // Commented out for v1.2
    }

    /**
     * Sets the {@code EventName} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setEventName(new EventName(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code EventStatus} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventStatus(EventStatus status) {
        descriptor.setEventStatus(status);
        return this;
    }

    // Commented out for v1.2
    //    /**
    //     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
    //     * that we are building.
    //     */
    //    public EditEventDescriptorBuilder withTags(String... tags) {
    //        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
    //        descriptor.setTags(tagSet);
    //        return this;
    //    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
