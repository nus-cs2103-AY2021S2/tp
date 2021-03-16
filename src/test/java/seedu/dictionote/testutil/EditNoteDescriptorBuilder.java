package seedu.dictionote.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.tag.Tag;

/**
 * A utility class to help with building EditNoteDescriptor objects.
 */
public class EditNoteDescriptorBuilder {

    private EditNoteDescriptor descriptor;

    public EditNoteDescriptorBuilder() {
        descriptor = new EditNoteDescriptor();
    }

    public EditNoteDescriptorBuilder(EditNoteDescriptor descriptor) {
        this.descriptor = new EditNoteDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditNoteDescriptorBuilder(Note note) {
        descriptor = new EditNoteDescriptor();
        descriptor.setNote(note);
    }

    /**
     * Sets the {@code Note} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditNoteDescriptor}
     * that we are building.
     */
    public EditNoteDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditNoteDescriptor build() {
        return descriptor;
    }
}
