package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditCardDescriptor;
import seedu.address.model.flashcard.Priority;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.Question;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditCardDescriptor objects.
 */
public class EditCardDescriptorBuilder {

    private EditCardDescriptor descriptor;

    public EditCardDescriptorBuilder() {
        descriptor = new EditCardDescriptor();
    }

    public EditCardDescriptorBuilder(EditCardDescriptor descriptor) {
        this.descriptor = new EditCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCardDescriptor} with fields containing {@code flashcard}'s details
     */
    public EditCardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditCardDescriptor();
        descriptor.setQuestion(flashcard.getName());
        descriptor.setAnswer(flashcard.getPhone());
        descriptor.setCategory(flashcard.getEmail());
        descriptor.setPriority(flashcard.getAddress());
        descriptor.setTags(flashcard.getTags());
    }

    /**
     * Sets the {@code Question} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new Question(question));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCaradDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCardDescriptor}
     * that we are building.
     */
    public EditCardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCardDescriptor build() {
        return descriptor;
    }
}
