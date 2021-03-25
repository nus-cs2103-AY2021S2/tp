package fooddiary.testutil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fooddiary.logic.commands.EditCommand;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditEntryDescriptorBuilder {

    private EditCommand.EditEntryDescriptor descriptor;

    public EditEntryDescriptorBuilder() {
        descriptor = new EditCommand.EditEntryDescriptor();
    }

    public EditEntryDescriptorBuilder(EditCommand.EditEntryDescriptor descriptor) {
        this.descriptor = new EditCommand.EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code entry}'s details
     */
    public EditEntryDescriptorBuilder(Entry entry) {
        descriptor = new EditCommand.EditEntryDescriptor();
        descriptor.setName(entry.getName());
        descriptor.setRating(entry.getRating());
        descriptor.setPrice(entry.getPrice());
        descriptor.setReviews(entry.getReviews());
        descriptor.setAddress(entry.getAddress());
        descriptor.setTags(entry.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Parses the {@code reviews} into a {@code List<Review>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditEntryDescriptorBuilder withReviews(String... reviews) {
        List<Review> reviewList = Stream.of(reviews).map(Review::new).collect(Collectors.toList());
        descriptor.setReviews(reviewList);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditEntryDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditEntryDescriptor build() {
        return descriptor;
    }
}
