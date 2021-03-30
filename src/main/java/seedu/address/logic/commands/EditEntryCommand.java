package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATED_ENTRY;
import static seedu.address.commons.core.Messages.MESSAGE_EDIT_ENTRY_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_ENTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTRIES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing entry in the address book.
 */
public class EditEntryCommand extends Command {

    public static final String COMMAND_WORD = "eedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the entry identified "
            + "by the entry name used in the displayed entries list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ENTRY_NAME "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_START_DATE + "START_DATE] "
            + "[" + PREFIX_END_DATE + "END_DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " Meeting "
            + PREFIX_NAME + "Meeting with group ";

    private final EntryName entryName;
    private final EditEntryDescriptor editEntryDescriptor;

    /**
     * @param entryName of the entry in the filtered entry list to edit
     * @param editEntryDescriptor details to edit the entry with
     */
    public EditEntryCommand(EntryName entryName, EditEntryDescriptor editEntryDescriptor) {
        requireNonNull(entryName);
        requireNonNull(editEntryDescriptor);

        this.entryName = entryName;
        this.editEntryDescriptor = new EditEntryDescriptor(editEntryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownEntryList = model.getFilteredEntryList();

        //Get entry with same entryName, case sensitive
        Entry entryToEdit = null;
        for (Entry entry : lastShownEntryList) {
            if (entryName.toString().equals(entry.getEntryName().toString())) {
                entryToEdit = entry;
                break;
            }
        }

        //Entry with same entryName does not exist
        if (entryToEdit == null) {
            throw new CommandException(MESSAGE_NO_SUCH_ENTRY);
        }

        Entry editedEntry = createEditedEntry(entryToEdit, editEntryDescriptor);

        if (!entryToEdit.isSameEntry(editedEntry) && model.hasEntry(editedEntry)) {
            throw new CommandException(MESSAGE_DUPLICATED_ENTRY);
        }

        model.setEntry(entryToEdit, editedEntry);
        model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        return new CommandResult(String.format(MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry));
    }

    /**
     * Creates and returns an {@code Entry} with the details of {@code entryToEdit}
     * edited with {@code editEntryDescriptor}.
     */
    private static Entry createEditedEntry(Entry entryToEdit, EditEntryDescriptor editEntryDescriptor) {
        assert entryToEdit != null;

        EntryName updatedEntryName = editEntryDescriptor.getEntryName().orElse(entryToEdit.getEntryName());
        EntryDate updatedStartDate = editEntryDescriptor.getStartDate().orElse(entryToEdit.getOriginalStartDate());
        EntryDate updatedEndDate = editEntryDescriptor.getEndDate().orElse(entryToEdit.getOriginalEndDate());
        Set<Tag> updatedTags = editEntryDescriptor.getTags().orElse(entryToEdit.getTags());

        return new Entry(updatedEntryName, updatedStartDate, updatedEndDate, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEntryCommand)) {
            return false;
        }

        // state check
        EditEntryCommand e = (EditEntryCommand) other;
        return entryName.equals(e.entryName)
                && editEntryDescriptor.equals(e.editEntryDescriptor);
    }

    /**
     * Stores the details to edit the entry with. Each non-empty field value will replace the
     * corresponding field value of the entry.
     */
    public static class EditEntryDescriptor {
        private EntryName entryName;
        private EntryDate startDate;
        private EntryDate endDate;
        private Set<Tag> tags;

        public EditEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEntryDescriptor(EditEntryDescriptor toCopy) {
            setEntryName(toCopy.entryName);
            setEntryStartDate(toCopy.startDate);
            setEntryEndDate(toCopy.endDate);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(entryName, startDate, endDate, tags);
        }

        public void setEntryName(EntryName entryName) {
            this.entryName = entryName;
        }

        public Optional<EntryName> getEntryName() {
            return Optional.ofNullable(entryName);
        }

        public void setEntryStartDate(EntryDate startDate) {
            this.startDate = startDate;
        }

        public Optional<EntryDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEntryEndDate(EntryDate endDate) {
            this.endDate = endDate;
        }

        public Optional<EntryDate> getEndDate() {
            return Optional.ofNullable(endDate);
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
            if (!(other instanceof EditEntryDescriptor)) {
                return false;
            }

            // state check
            EditEntryDescriptor e = (EditEntryDescriptor) other;

            return getEntryName().equals(e.getEntryName())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate())
                    && getTags().equals(e.getTags());
        }
    }
}
