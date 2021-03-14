package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.commons.util.CollectionUtil;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.tag.Tag;

/**
 * Edits the details of an existing contact in the contacts list.
 */
public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "editnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the note identified "
            + "by the index number used in the displayed note list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CONTENT + "CONTENT] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CONTENT + "Study for CS2106 Midterms";

    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited note: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note is not changed.";

    private final Index index;
    private final EditNoteDescriptor editNoteDescriptor;

    /**
     * @param index of the contact in the filtered contacts to edit
     * @param editNoteDescriptor details to edit the contact with
     */
    public EditNoteCommand(Index index, EditNoteDescriptor editNoteDescriptor) {
        requireNonNull(index);
        requireNonNull(editNoteDescriptor);

        this.index = index;
        this.editNoteDescriptor = new EditNoteDescriptor(editNoteDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Note> lastShownList = model.getFilteredNoteList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToEdit = lastShownList.get(index.getZeroBased());
        Note editedNote = createEditedNote(noteToEdit, editNoteDescriptor);

        if (!noteToEdit.isSameNote(editedNote) && model.hasNote(editedNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.setNote(noteToEdit, editedNote);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, editedNote));
    }

    /**
     * Creates and returns a {@code Note} with the details of {@code noteToEdit}
     * edited with {@code editNoteDescriptor}.
     */
    private static Note createEditedNote(Note noteToEdit, EditNoteDescriptor editNoteDescriptor) {
        assert noteToEdit != null;

        Note updatedNote = editNoteDescriptor.getNote().orElse(noteToEdit);
        Set<Tag> updatedTags = editNoteDescriptor.getTags().orElse(noteToEdit.getTags());
        return new Note(updatedNote.getNote(), updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditNoteCommand e = (EditNoteCommand) other;
        return index.equals(e.index)
                && editNoteDescriptor.equals(e.editNoteDescriptor);
    }

    /**
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditNoteDescriptor {
        private Note note;
        private Set<Tag> tags;

        public EditNoteDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditNoteDescriptor(EditNoteDescriptor toCopy) {
            setNote(toCopy.note);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(note, tags);
        }

        public void setNote(Note note) {
            this.note = note;
        }
        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditNoteDescriptor)) {
                return false;
            }

            // state check
            EditNoteDescriptor e = (EditNoteDescriptor) other;

            return getNote().equals(e.getNote())
                    && getTags().equals(e.getTags());
        }
    }
}
