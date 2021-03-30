package seedu.address.logic.commands.connections;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.*;

import static java.util.Objects.requireNonNull;

public class AddPersonToMeetingConnectionCommand extends Command {
    public static final String COMMAND_WORD = "addptm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds persons by index or by groups to a meeting. "
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

    private static String MESSAGE_SUCCESS = "Successfully add persons related to the meeting! "
            + "The possible duplication of persons related is automatically removed.";

    private final Index meetingIndex;
    private final Set<Group> groupsToAdd = new HashSet<>();
    private final Set<Index> personsIndexToAdd = new HashSet<>();
    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param personsIndexToAdd the set of index of persons the users want to add.
     * @param groupsToAdd the set of index of groups the users want to add.
     */
    public AddPersonToMeetingConnectionCommand(Index index, Set<Index> personsIndexToAdd, Set<Group> groupsToAdd) {
        requireNonNull(index);
        meetingIndex = index;
        this.personsIndexToAdd.addAll(personsIndexToAdd);
        this.groupsToAdd.addAll(groupsToAdd);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (meetingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meetingToEdit = lastShownList.get(meetingIndex.getZeroBased());
        Set<Person> existedPersonsConnection = meetingToEdit.getConnectionToPerson();
        model.deleteAllPersonMeetingConnectionByMeeting(meetingToEdit);
        addConnectionsToPersons(meetingToEdit, model, existedPersonsConnection);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * This method will handle the connections that the user wants to add from both the g/ and p/
     * Duplicate person that the user wants to build connection with this meeting will be automatically removed.
     */
    private void addConnectionsToPersons(Meeting toAdd, Model model, Set<Person> existedPersonsConnection) throws CommandException {
        // Use set to ensure unique element.
        HashSet<Person> personsConnection = new HashSet<>();
        personsConnection.addAll(existedPersonsConnection);
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());
        if (!groupsToAdd.isEmpty()) {
            toAdd.addGroups(groupsToAdd);
            for (Group group : groupsToAdd) {
                Set<Person> personsInGroup = model.findPersonsInGroup(group);
                // Get the union set.
                personsConnection.addAll(personsInGroup);
            }
        }

        if (personsIndexToAdd.size() != 0) {
            List<Person> lastShownList = model.getFilteredPersonList();
            // Check whether the index is out of bounds
            for (Index index : personsIndexToAdd) {
                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
            }
            // If we can pass the check, then add connection.
            for (Index index: personsIndexToAdd) {
                Person personToAddConnection = lastShownList.get(index.getZeroBased());
                personsConnection.add(personToAddConnection);
            }
        }

        for (Person allPersonToAddConnection : personsConnection) {
            model.addPersonMeetingConnection(allPersonToAddConnection, toAdd);
        }
    }
}
