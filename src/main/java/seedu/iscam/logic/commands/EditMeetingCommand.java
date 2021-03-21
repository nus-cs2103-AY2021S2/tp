package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.commons.util.CollectionUtil;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Location;
import seedu.iscam.model.client.Name;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.tag.Tag;

/**
 * Edits the details of an existing Meeting in the iscam book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editmeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLIENT + "CLIENT_ID]"
            + "[" + PREFIX_ON + "DATE_TIME]"
            + "[" + PREFIX_LOCATION + "ADDRESS]"
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LOCATION + "Macdonald, Bedok"
            + PREFIX_DESCRIPTION + "Client's family will be coming along";
    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This meeting already exists in the iscam book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * Edit a Meeting specified by an index and their edited fields
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
        LocalDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Location updatedLocation = editMeetingDescriptor.getAddress().orElse(meetingToEdit.getLocation());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());

        return new Meeting(updatedClientName, updatedDateTime, updatedLocation, updatedDescription, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Get a list of Meetings from model

        // Throw exception if specified index is out of range

        // Get Meeting specified by the index
        // Create an editing Meeting based on that Meeting

        // Throw exception if that edited Meeting is a duplicate of the original

        // Update Model and Meeting list

        return new CommandResult("PLACEHOLDER EDIT SUCCESS");
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the corresponding field
     * value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Name clientName;
        private LocalDateTime dateTime;
        private Location location;
        private Description description;
        private Set<Tag> tags;

        public EditMeetingDescriptor() {
        }

        /**
         * Copy constructor to copy field values from previous version of the descriptor.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setClientName(toCopy.clientName);
            setDateTime(toCopy.dateTime);
            setAddress(toCopy.location);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(clientName, dateTime, location, description, tags);
        }

        public Optional<Name> getClientName() {
            return Optional.ofNullable(clientName);
        }

        public void setClientName(Name clientName) {
            this.clientName = clientName;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<Location> getAddress() {
            return Optional.ofNullable(location);
        }

        public void setAddress(Location location) {
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
                    && getAddress().equals(e.getAddress())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
