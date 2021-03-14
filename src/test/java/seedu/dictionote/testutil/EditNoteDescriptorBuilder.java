package seedu.dictionote.testutil;

import java.util.HashSet;

import seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.dictionote.model.note.Note;

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
        descriptor.setNote(new Note(note, new HashSet<>()));
        return this;
    }

    public EditNoteDescriptor build() {
        return descriptor;
    }
}
