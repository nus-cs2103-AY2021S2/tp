package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.flashcard.Answer;
import seedu.address.model.flashcard.Category;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Priority;
import seedu.address.model.flashcard.Question;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditFlashcardDescriptor objects.
 */
public class EditFlashcardDescriptorBuilder {

    private EditCommand.EditFlashcardDescriptor descriptor;

    public EditFlashcardDescriptorBuilder() {
        descriptor = new EditFlashcardDescriptor();
    }

    public EditFlashcardDescriptorBuilder(EditCommand.EditFlashcardDescriptor descriptor) {
        this.descriptor = new EditCommand.EditFlashcardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFlashcardDescriptor} with fields containing {@code flashcard}'s details
     */
    public EditFlashcardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditCommand.EditFlashcardDescriptor();
        descriptor.setQuestion(flashcard.getQuestion());
        descriptor.setAnswer(flashcard.getAnswer());
        descriptor.setCategory(flashcard.getCategory());
        descriptor.setPriority(flashcard.getPriority());
        descriptor.setTags(flashcard.getTags());
    }

    /**
     * Sets the {@code Question} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withQuestion(String name) {
        descriptor.setQuestion(new Question(name));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withAnswer(String phone) {
        descriptor.setAnswer(new Answer(phone));
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withCategory(String email) {
        descriptor.setCategory(new Category(email));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withPriority(String address) {
        descriptor.setPriority(new Priority(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFlashcardDescriptor}
     * that we are building.
     */
    public EditFlashcardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditFlashcardDescriptor build() {
        return descriptor;
    }
}
