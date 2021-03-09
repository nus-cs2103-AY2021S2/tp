package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

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
    public static final String MESSAGE_DUPLICATE_CLIENT = "This meeting already exists in the address book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

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

    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        Client updatedClient = editMeetingDescriptor.getClient().orElse(meetingToEdit.getClient());
        LocalDateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Address updatedAddress = editMeetingDescriptor.getAddress().orElse(meetingToEdit.getAddress());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        Set<Tag> updatedTags = editMeetingDescriptor.getTags().orElse(meetingToEdit.getTags());

        return new Meeting(updatedClient, updatedDateTime, updatedAddress, updatedDescription, updatedTags);
    }

    public static class EditMeetingDescriptor {
        private Client client;
        private LocalDateTime dateTime;
        private Address address;
        private Description description;
        private Set<Tag> tags;

        public EditMeetingDescriptor() {}

        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setClient(toCopy.client);
            setDateTime(toCopy.dateTime);
            setAddress(toCopy.address);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(client, dateTime, address, description, tags);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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
