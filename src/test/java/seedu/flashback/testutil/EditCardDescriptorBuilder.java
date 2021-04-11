package seedu.flashback.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.flashback.logic.commands.EditCommand.EditCardDescriptor;
import seedu.flashback.model.flashcard.Answer;
import seedu.flashback.model.flashcard.Category;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.Priority;
import seedu.flashback.model.flashcard.Question;
import seedu.flashback.model.tag.Tag;

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
        descriptor.setQuestion(flashcard.getQuestion());
        descriptor.setAnswer(flashcard.getAnswer());
        descriptor.setCategory(flashcard.getCategory());
        descriptor.setPriority(flashcard.getPriority());
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
