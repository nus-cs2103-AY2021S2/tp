package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing issue in the address book.
 */
public class EditIssueCommand extends Command {

    public static final String COMMAND_WORD = "iedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the issue identified "
            + "by the index number used in the displayed issue list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ROOM_NUMBER + "ROOM_NO] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TIMESTAMP + "TIMESTAMP] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]"
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Broken window "
            + PREFIX_CATEGORY + "Window";

    public static final String MESSAGE_EDIT_ISSUE_SUCCESS = "Edited Issue: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ISSUE = "This issue already exists in the address book.";

    private final Index index;
    private final EditIssueDescriptor editIssueDescriptor;

    /**
     * @param index               of the issue in the filtered issue list to edit
     * @param editIssueDescriptor details to edit the issue with
     */
    public EditIssueCommand(Index index, EditIssueDescriptor editIssueDescriptor) {
        requireNonNull(index);
        requireNonNull(editIssueDescriptor);

        this.index = index;
        this.editIssueDescriptor = new EditIssueDescriptor(editIssueDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX);
        }

        Issue issueToEdit = lastShownList.get(index.getZeroBased());
        Issue editedIssue = createEditedIssue(issueToEdit, editIssueDescriptor);

        model.setIssue(issueToEdit, editedIssue);
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
        return new CommandResult(String.format(MESSAGE_EDIT_ISSUE_SUCCESS, editedIssue));
    }

    /**
     * Creates and returns a {@code Issue} with the details of {@code issueToEdit}
     * edited with {@code editIssueDescriptor}.
     */
    private static Issue createEditedIssue(Issue issueToEdit, EditIssueDescriptor editIssueDescriptor) {
        assert issueToEdit != null;

        RoomNumber updatedRoomNumber = editIssueDescriptor.getRoomNumber().orElse(issueToEdit.getRoomNumber());
        Description updatedDescription = editIssueDescriptor.getDescription()
                .orElse(issueToEdit.getDescription());
        Timestamp updatedTimestamp = editIssueDescriptor.getTimestamp().orElse(issueToEdit.getTimestamp());
        Status updatedStatus = editIssueDescriptor.getStatus().orElse(issueToEdit.getStatus());
        Category updatedCategory = editIssueDescriptor.getCategory().orElse(issueToEdit.getCategory());
        Set<Tag> updatedTags = editIssueDescriptor.getTags().orElse(issueToEdit.getTags());

        return new Issue(updatedRoomNumber, updatedDescription, updatedTimestamp, updatedStatus, updatedCategory,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditIssueCommand)) {
            return false;
        }

        // state check
        EditIssueCommand e = (EditIssueCommand) other;
        return index.equals(e.index)
                && editIssueDescriptor.equals(e.editIssueDescriptor);
    }

    /**
     * Stores the details to edit the issue with. Each non-empty field value will replace the
     * corresponding field value of the issue.
     */
    public static class EditIssueDescriptor {
        private RoomNumber roomNumber;
        private Description description;
        private Timestamp timestamp;
        private Status status;
        private Category category;
        private Set<Tag> tags;

        public EditIssueDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditIssueDescriptor(EditIssueDescriptor toCopy) {
            setRoomNumber(toCopy.roomNumber);
            setDescription(toCopy.description);
            setTimestamp(toCopy.timestamp);
            setStatus(toCopy.status);
            setCategory(toCopy.category);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(roomNumber, description, timestamp, status, category, tags);
        }

        public void setRoomNumber(RoomNumber name) {
            this.roomNumber = name;
        }

        public Optional<RoomNumber> getRoomNumber() {
            return Optional.ofNullable(roomNumber);
        }

        public void setDescription(Description phone) {
            this.description = phone;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setTimestamp(Timestamp email) {
            this.timestamp = email;
        }

        public Optional<Timestamp> getTimestamp() {
            return Optional.ofNullable(timestamp);
        }

        public void setStatus(Status address) {
            this.status = address;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code category} to this object's {@code category}.
         * A defensive copy of {@code category} is used internally.
         */
        public void setCategory(Category category) {
            this.category = category;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code category} is null.
         */
        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
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
            if (!(other instanceof EditIssueDescriptor)) {
                return false;
            }

            // state check
            EditIssueDescriptor e = (EditIssueDescriptor) other;

            return getRoomNumber().equals(e.getRoomNumber())
                    && getDescription().equals(e.getDescription())
                    && getTimestamp().equals(e.getTimestamp())
                    && getStatus().equals(e.getStatus())
                    && getCategory().equals(e.getCategory())
                    && getTags().equals(e.getTags());
        }
    }
}
