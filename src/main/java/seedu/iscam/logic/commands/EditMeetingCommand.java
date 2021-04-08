package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.commons.util.CollectionUtil;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.CompletionStatus;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;

/**
 * Edits the details of an existing Meeting in the iScam book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editmeet";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLIENT + "CLIENT_NAME] "
            + "[" + PREFIX_ON + "DATE_TIME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG(s)] "
            + "[" + PREFIX_STATUS + "COMPLETION_STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LOCATION + "Macdonald, Bedok "
            + PREFIX_DESCRIPTION + "Client's family will be coming along";
    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NO_CHANGES = "No changes found in any field.";
    public static final String MESSAGE_CONFLICT = "There is another meeting with the same date and time, consider "
            + "changing to another time.";
    public static final String MESSAGE_NOT_ALLOWED = "This meeting was already completed, no modification can be made "
            + "unless it is set back to incomplete.";
    public static final String MESSAGE_ALREADY_COMPLETE = "This meeting was already completed, it cannot be completed "
            + "again.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * Edits a Meeting specified by an index and their edited fields.
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        Name updatedClientName = editMeetingDescriptor.getClientName().orElse(meetingToEdit.getClientName());
        DateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Location updatedLocation = editMeetingDescriptor.getLocation().orElse(meetingToEdit.getLocation());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());
        CompletionStatus updatedStatus = editMeetingDescriptor.getStatus().orElse(meetingToEdit.getStatus());

        return new Meeting(updatedClientName, updatedDateTime, updatedLocation, updatedDescription, updatedTags,
                updatedStatus);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        if (index.getZeroBased() >= meetings.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = meetings.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meeting, editMeetingDescriptor);
        Optional<CompletionStatus> editedStatus = editMeetingDescriptor.getStatus();

        if (meeting.getStatus().isComplete() && editedStatus.isEmpty()) {
            throw new CommandException(MESSAGE_NOT_ALLOWED);
        } else if (meeting.getStatus().isComplete() && editedStatus.get().isComplete()) {
            throw new CommandException(MESSAGE_ALREADY_COMPLETE);
        }

        if (meeting.equals(editedMeeting)) {
            throw new CommandException(MESSAGE_NO_CHANGES);
        }

        if (model.hasConflictingMeetingWith(editedMeeting, meeting)) {
            throw new CommandException(MESSAGE_CONFLICT);
        }

        model.setMeeting(meeting, editedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the corresponding field
     * value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Name clientName;
        private DateTime dateTime;
        private Location location;
        private Description description;
        private Set<Tag> tags;
        private CompletionStatus status;

        public EditMeetingDescriptor() {
        }

        /**
         * Copy constructor to copy field values from previous version of the descriptor.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setClientName(toCopy.clientName);
            setDateTime(toCopy.dateTime);
            setLocation(toCopy.location);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(clientName, dateTime, location, description, tags, status);
        }

        public Optional<Name> getClientName() {
            return Optional.ofNullable(clientName);
        }

        public void setClientName(Name clientName) {
            this.clientName = clientName;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setTags(Set<Tag> tags) {
            this.tags = tags != null ? new HashSet<>(tags) : null;
        }

        public Optional<CompletionStatus> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setStatus(CompletionStatus status) {
            this.status = status;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getClientName().equals(e.getClientName())
                    && getDateTime().equals(e.getDateTime())
                    && getLocation().equals(e.getLocation())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags())
                    && getStatus().equals(e.getStatus());
        }
    }
}
