package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meetings.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "deletep";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeletePersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        // If this person is related to some meetings...
        if (!model.getFilteredMeetingListByPersonConnection(personToDelete).isEmpty()) {
            updatePersonMeetingConnection(personToDelete, model);
            model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePersonCommand) other).targetIndex)); // state check
    }

    private void updatePersonMeetingConnection(Person personToDelete, Model model) {
        UniqueMeetingList toDeletePersonRelatedMeetings = model.getUniqueMeetingListByPersonConnection(personToDelete);
        Set<Meeting> toDeletePersonRelatedMeetingsSet = new HashSet<>();
        // Do a deep copy
        for (Meeting meeting : toDeletePersonRelatedMeetings) {
            toDeletePersonRelatedMeetingsSet.add(meeting);
        }
        // Start to edit connection(i.e. reconstruct connection)
        for (Meeting meetingToEdit : toDeletePersonRelatedMeetingsSet) {
            Meeting meetingEdited = createEditedMeeting(meetingToEdit, new EditMeetingDescriptor());
            deleteConnectionsToPersons(meetingToEdit, meetingEdited, model, personToDelete);
            model.updateMeeting(meetingToEdit, meetingEdited);
        }
        model.deleteAllPersonMeetingConnectionByPerson(personToDelete);
    }

    private void deleteConnectionsToPersons(Meeting toDelete, Meeting toAdd, Model model, Person personToDelete) {
        Set<Person> prevPersonsConnection = toDelete.getConnectionToPerson();
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());

        prevPersonsConnection.remove(personToDelete);
        Set<Person> updatedPersonsConnection = prevPersonsConnection;

        model.deleteAllPersonMeetingConnectionByMeeting(toDelete);

        for (Person allPersonToAddConnection : updatedPersonsConnection) {
            model.addPersonMeetingConnection(allPersonToAddConnection, toAdd);
        }
    }
    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        MeetingName updatedMeetingName = editMeetingDescriptor
                .getName()
                .orElse(meetingToEdit.getName());
        DateTime updatedStart = editMeetingDescriptor
                .getStart()
                .orElse(meetingToEdit.getStart());
        DateTime updatedTerminate = editMeetingDescriptor
                .getTerminate()
                .orElse(meetingToEdit.getTerminate());
        Priority updatedPriority = editMeetingDescriptor
                .getPriority()
                .orElse(meetingToEdit.getPriority());
        Description updatedDescription = editMeetingDescriptor
                .getDescription()
                .orElse(meetingToEdit.getDescription());
        Set<Group> updatedGroups = editMeetingDescriptor
                .getGroups()
                .orElse(meetingToEdit.getGroups());

        return new Meeting(updatedMeetingName, updatedStart,
                updatedTerminate, updatedPriority, updatedDescription, updatedGroups);
    }
}
