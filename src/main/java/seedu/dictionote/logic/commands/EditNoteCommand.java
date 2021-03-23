package seedu.dictionote.logic.commands;

import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.commons.util.CollectionUtil;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
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
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the note list.";
    public static final String MESSAGE_NOTHING_CHANGE_NOTE = "This note have not been change.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

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

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        List<Note> lastShownList = model.getFilteredNoteList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        Note noteToEdit = lastShownList.get(index.getZeroBased());
        Note editedNote = createEditedNote(noteToEdit, editNoteDescriptor);

        if (!noteToEdit.isSameNote(editedNote)) {
            throw new CommandException(MESSAGE_NOTHING_CHANGE_NOTE);
        }

        if (model.hasNote(editedNote)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.setNote(noteToEdit, editedNote);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_EDIT_NOTE_SUCCESS, editedNote),
            UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

    /**
     * Creates and returns a {@code Note} with the details of {@code noteToEdit}
     * edited with {@code editNoteDescriptor}.
     */
    private static Note createEditedNote(Note noteToEdit, EditNoteDescriptor editNoteDescriptor) {
        assert noteToEdit != null;

        Note updatedNote = editNoteDescriptor.getNote().orElse(noteToEdit);
        Set<Tag> updatedTags = editNoteDescriptor.getTags().orElse(noteToEdit.getTags());
        return updatedNote.createEditedNote(updatedNote.getNote(), updatedTags,
                noteToEdit.getCreateTime(), now(), updatedNote.isDone());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditNoteCommand)) {
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
        private LocalDateTime createTime;
        private LocalDateTime editTime;
        private Boolean isDone;
        public EditNoteDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditNoteDescriptor(EditNoteDescriptor toCopy) {
            setNote(toCopy.note);
            setTags(toCopy.tags);
            setCreateTime(toCopy.createTime);
            setEditTime(now());
            setIsDone(toCopy.isDone);
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

        /**
         * Sets {@code createTime} to this object's {@code createTime}.
         * A defensive copy of {@code createTime} is used internally.
         */
        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        /**
         * Returns an unmodifiable createTime, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code createTime} is null.
         */
        public Optional<LocalDateTime> getCreateTime() {
            return Optional.ofNullable(createTime);
        }

        /**
         * Sets {@code editTime} to this object's {@code editTime}.
         * A defensive copy of {@code editTime} is used internally.
         */
        public void setEditTime(LocalDateTime editTime) {
            this.editTime = editTime;
        }

        /**
         * Returns an unmodifiable editTime, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code editTime} is null.
         */
        public Optional<LocalDateTime> getEditTime() {
            return Optional.ofNullable(editTime);
        }


        /**
         * Sets {@code isDone} to this object's {@code isDone}.
         * A defensive copy of {@code isDone} is used internally.
         */
        public void setIsDone(Boolean isDone) {
            this.isDone = isDone;
        }

        public Optional<Boolean> getIsDone() {
            return Optional.ofNullable(this.isDone);
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
