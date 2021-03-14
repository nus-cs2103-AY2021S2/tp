package seedu.iScam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.iScam.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.iScam.commons.core.index.Index;
import seedu.iScam.commons.util.CollectionUtil;
import seedu.iScam.logic.commands.exceptions.CommandException;
import seedu.iScam.model.Model;
import seedu.iScam.model.client.Location;
import seedu.iScam.model.client.Client;
import seedu.iScam.model.meeting.Description;
import seedu.iScam.model.meeting.Meeting;
import seedu.iScam.model.tag.Tag;

/**
 * Edits the details of an existing Meeting in the iScam book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editmeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLIENT + "CLIENT_ID]"
            + "[" + PREFIX_ON + "DATE_TIME]"
            + "[" + PREFIX_ADDRESS + "ADDRESS]"
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ADDRESS + "Macdonald, Bedok"
            + PREFIX_DESCRIPTION + "Client's family will be coming along";
    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This meeting already exists in the iScam book.";

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
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        Client updatedClient = editMeetingDescriptor.getClient().orElse(meetingToEdit.getClient());
        LocalDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Location updatedLocation = editMeetingDescriptor.getAddress().orElse(meetingToEdit.getAddress());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());

        return new Meeting(updatedClient, updatedDateTime, updatedLocation, updatedDescription, updatedTags);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the corresponding field
     * value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Client client;
        private LocalDateTime dateTime;
        private Location location;
        private Description description;
        private Set<Tag> tags;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor to copy field values from previous version of the descriptor.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setClient(toCopy.client);
            setDateTime(toCopy.dateTime);
            setAddress(toCopy.location);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(client, dateTime, location, description, tags);
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public Optional<Client> getClient() {
            return Optional.ofNullable(client);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setAddress(Location location) {
            this.location = location;
        }

        public Optional<Location> getAddress() {
            return Optional.ofNullable(location);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setTags(Set<Tag> tags) {
            this.tags = tags != null ? new HashSet<>(tags) : null;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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

            return getClient().equals(e.getClient())
                    && getDateTime().equals(e.getDateTime())
                    && getAddress().equals(e.getAddress())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
