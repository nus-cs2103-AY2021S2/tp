package fooddiary.testutil;

import fooddiary.logic.commands.EditCommand;
import fooddiary.model.entry.*;
import fooddiary.model.tag.Tag;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCommand.EditEntryDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCommand.EditEntryDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditEntryDescriptor descriptor) {
        this.descriptor = new EditCommand.EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Entry entry) {
        descriptor = new EditCommand.EditEntryDescriptor();
        descriptor.setName(entry.getName());
        descriptor.setRating(entry.getRating());
        descriptor.setReview(entry.getReview());
        descriptor.setAddress(entry.getAddress());
        descriptor.setTags(entry.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Review} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withReview(String review) {
        descriptor.setReview(new Review(review));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditEntryDescriptor build() {
        return descriptor;
    }
}
