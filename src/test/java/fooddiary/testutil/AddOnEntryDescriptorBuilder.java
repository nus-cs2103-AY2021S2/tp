package fooddiary.testutil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fooddiary.logic.commands.AddOnCommand;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Review;

/**
 * A utility class to help with building AddOnPersonDescriptor objects.
 */
public class AddOnEntryDescriptorBuilder {

    private AddOnCommand.AddOnToEntryDescriptor descriptor;

    public AddOnEntryDescriptorBuilder() {
        descriptor = new AddOnCommand.AddOnToEntryDescriptor();
    }

    public AddOnEntryDescriptorBuilder(AddOnCommand.AddOnToEntryDescriptor descriptor) {
        this.descriptor = new AddOnCommand.AddOnToEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddOnPersonDescriptor} with fields containing {@code entry}'s details
     */
    public AddOnEntryDescriptorBuilder(Entry entry) {
        descriptor = new AddOnCommand.AddOnToEntryDescriptor();
        descriptor.setReviews(entry.getReviews());
    }

    /**
     * Parses the {@code reviews} into a {@code List<Review>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public AddOnEntryDescriptorBuilder withReviews(String... reviews) {
        List<Review> reviewList = Stream.of(reviews).map(Review::new).collect(Collectors.toList());
        descriptor.setReviews(reviewList);
        return this;
    }


    public AddOnCommand.AddOnToEntryDescriptor build() {
        return descriptor;
    }
}
