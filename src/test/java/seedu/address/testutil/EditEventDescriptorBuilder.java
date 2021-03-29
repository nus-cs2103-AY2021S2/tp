package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Event;
import seedu.address.model.event.Time;

/**
 * A utility class to help with building EditEventDescriptor objects.
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
        descriptor.setName(event.getName());
        descriptor.setStartDate(event.getStartDate());
        descriptor.setStartTime(event.getStartTime());
        descriptor.setEndDate(event.getEndDate());
        descriptor.setEndTime(event.getEndTime());
        descriptor.setCategories(event.getCategories());
        descriptor.setTags(event.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new Date(startDate));
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(new Time(startTime));
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new Date(endDate));
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEndTime(String endTime) {
        descriptor.setEndTime(new Time(endTime));
        return this;
    }

    /**
     * Sets the {@code Categories} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withCategories(String... categories) {
        Set<Category> categorySet = Stream.of(categories).map(Category::new).collect(Collectors.toSet());
        descriptor.setCategories(categorySet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEventDescriptor}
     * that we are building.
     */
    public EditEventDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
