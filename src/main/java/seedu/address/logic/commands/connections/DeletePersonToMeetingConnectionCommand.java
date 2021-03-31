package seedu.address.logic.commands.connections;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meetings.EditMeetingCommand;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.*;
import seedu.address.model.person.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

public class DeletePersonToMeetingConnectionCommand extends Command {
    public static final String COMMAND_WORD = "deletepfm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes persons by index or by groups from a meeting. "
            + "Parameters: "
            + "INDEX of the meeting (must be a positive integer) "
            + "[" + PREFIX_GROUP + "GROUP]..."
            + "[" + PREFIX_PERSON_CONNECTION + "INDEX OF PERSON RELATED]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_GROUP + "lectures "
            + PREFIX_GROUP + "SoC "
            + PREFIX_PERSON_CONNECTION + "1 "
            + PREFIX_PERSON_CONNECTION + "2";

    private static String MESSAGE_SUCCESS = "Successfully delete persons related to the meeting! ";
    private static String MESSAGE_GROUP_NOT_EXIST = "The group related to this meeting doesn't exist!";
    private static String MESSAGE_PERSON_NOT_EXIST = "The person related to this meeting doesn't exist!";
    private final Index meetingIndex;
    private final Set<Group> groupsToDelete = new HashSet<>();
    private final Set<Index> personsIndexToDelete = new HashSet<>();
    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param personsIndexToAdd the set of index of persons the users want to delete.
     * @param groupsToAdd the set of index of groups the users want to delete.
     */
    public DeletePersonToMeetingConnectionCommand(Index index, Set<Index> personsIndexToAdd, Set<Group> groupsToAdd) {
        requireNonNull(index);
        meetingIndex = index;
        this.personsIndexToDelete.addAll(personsIndexToAdd);
        this.groupsToDelete.addAll(groupsToAdd);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (meetingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meetingToEdit = lastShownList.get(meetingIndex.getZeroBased());
        Meeting meetingEdited = createEditedMeeting(meetingToEdit, new EditMeetingCommand.EditMeetingDescriptor());
        Set<Person> prevPersonsConnection = meetingToEdit.getConnectionToPerson();
        model.deleteAllPersonMeetingConnectionByMeeting(meetingToEdit);
        deleteConnectionsToPersons(meetingToEdit, meetingEdited, model, prevPersonsConnection);
        model.updateMeeting(meetingToEdit, meetingEdited);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
    /**
     * This method will handle the connections that the user wants to add from both the g/ and p/
     * Duplicate person that the user wants to build connection with this meeting will be automatically removed.
     */
    private void deleteConnectionsToPersons(Meeting toDelete, Meeting toAdd, Model model, Set<Person> prevPersonsConnection) throws CommandException {
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());
        Set<Person> totalPersonsToRemove = new HashSet<>();
        if (!groupsToDelete.isEmpty()) {
            for (Group group : groupsToDelete) {
                if (!toDelete.getGroups().contains(group)) {
                    throw new CommandException(MESSAGE_GROUP_NOT_EXIST);
                }
            }
            toAdd.deleteGroups(groupsToDelete);
            for (Group group : groupsToDelete) {
                Set<Person> personsInGroup = model.findPersonsInGroup(group);
                // Get the union set.
                totalPersonsToRemove.addAll(personsInGroup);
            }
        }

        if (personsIndexToDelete.size() != 0) {
            List<Person> lastShownList = model.getFilteredPersonList();
            // Check whether the index is out of bounds
            for (Index index : personsIndexToDelete) {
                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
            }
            // Check whether the person the user wants to delete is in the meetings to be edited.
            for (Index index : personsIndexToDelete) {
                Person personToDeleteConnection = lastShownList.get(index.getZeroBased());
                if (!prevPersonsConnection.contains(personToDeleteConnection)) {
                    throw new CommandException(MESSAGE_PERSON_NOT_EXIST);
                }
            }
            for (Index index : personsIndexToDelete) {
                Person personToAddConnection = lastShownList.get(index.getZeroBased());
                totalPersonsToRemove.add(personToAddConnection);
            }
        }

        prevPersonsConnection.removeAll(totalPersonsToRemove);
        Set<Person> updatedPersonsConnection = prevPersonsConnection;

        for (Person allPersonToAddConnection : updatedPersonsConnection) {
            model.addPersonMeetingConnection(allPersonToAddConnection, toAdd);
        }
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor) {
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
