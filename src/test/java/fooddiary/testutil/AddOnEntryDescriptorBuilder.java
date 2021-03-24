package fooddiary.testutil;

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
        descriptor.setReview(entry.getReview());
    }

    /**
     * Sets the {@code Review} of the {@code AddOnPersonDescriptor} that we are building.
     */
    public AddOnEntryDescriptorBuilder withReview(String review) {
        descriptor.setReview(new Review(review));
        return this;
    }


    public AddOnCommand.AddOnToEntryDescriptor build() {
        return descriptor;
    }
}
