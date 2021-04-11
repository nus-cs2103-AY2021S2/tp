package seedu.address.logic.commands.connections;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
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
import seedu.address.model.person.Person;


public class AddPersonToMeetingConnectionCommand extends Command {
    public static final String COMMAND_WORD = "addptm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds persons by index or by groups to a meeting. "
            + "Parameters: "
            + "INDEX of the meeting (must be a positive integer) "
            + "[" + PREFIX_PERSON_CONNECTION + "INDEX OF PERSON RELATED]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_PERSON_CONNECTION + "1 "
            + PREFIX_PERSON_CONNECTION + "2";

    private static String MESSAGE_SUCCESS = "Successfully add persons related to the meeting! "
            + "The possible duplication of persons related is automatically removed.";
    private static String MESSAGE_NO_PERSON_FOUND = "Please input the contact's index.";
    private final Index meetingIndex;
    private final Set<Index> personsIndexToAdd = new HashSet<>();
    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param personsIndexToAdd the set of index of persons the users want to add.
     */
    public AddPersonToMeetingConnectionCommand(Index index, Set<Index> personsIndexToAdd) {
        requireNonNull(index);
        requireNonNull(personsIndexToAdd);
        meetingIndex = index;
        this.personsIndexToAdd.addAll(personsIndexToAdd);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (meetingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meetingToEdit = lastShownList.get(meetingIndex.getZeroBased());
        Meeting meetingEdited = createEditedMeeting(meetingToEdit, new EditMeetingDescriptor());
        Set<Person> existedPersonsConnection = meetingToEdit.getConnectionToPerson();
        model.deleteAllPersonMeetingConnectionByMeeting(meetingToEdit);
        addConnectionsToPersons(meetingEdited, model, existedPersonsConnection);
        model.updateMeeting(meetingToEdit, meetingEdited);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * This method will handle the connections that the user wants to add from both the g/ and p/
     * Duplicate person that the user wants to build connection with this meeting will be automatically removed.
     */
    private void addConnectionsToPersons(Meeting toAdd, Model model,
                                         Set<Person> existedPersonsConnection) throws CommandException {
        // Use set to ensure unique element.
        HashSet<Person> personsConnection = new HashSet<>();
        personsConnection.addAll(existedPersonsConnection);
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());

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
        } else {
            throw new CommandException(MESSAGE_NO_PERSON_FOUND);
        }

        for (Person allPersonToAddConnection : personsConnection) {
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonToMeetingConnectionCommand // instanceof handles nulls
                && meetingIndex.equals(((AddPersonToMeetingConnectionCommand) other).meetingIndex)
                && personsIndexToAdd.equals(((AddPersonToMeetingConnectionCommand) other).personsIndexToAdd));
    }
}
