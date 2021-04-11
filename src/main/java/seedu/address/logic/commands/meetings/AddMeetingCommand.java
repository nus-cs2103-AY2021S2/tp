package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;


public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to MeetBuddy. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + "[" + PREFIX_GROUP + "GROUP]..."
            + "[" + PREFIX_PERSON_CONNECTION + "INDEX OF PERSON RELATED]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103 Lecture "
            + PREFIX_START_TIME + "2021-03-12 14:00 "
            + PREFIX_END_TIME + "2021-03-12 16:00 "
            + PREFIX_DESCRIPTION + "Week 7 "
            + PREFIX_PRIORITY + "3 "
            + PREFIX_GROUP + "lectures "
            + PREFIX_GROUP + "SoC "
            + PREFIX_PERSON_CONNECTION + "1 "
            + PREFIX_PERSON_CONNECTION + "2";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in MeetBuddy";
    public static final String MESSAGE_CLASH_MEETING = "This meeting clashes with the following existing meetings \n%s";

    private final Meeting toAdd;
    private final Set<Index> connectionToPerson = new HashSet<>();

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    /**
     * Set the connection indices to persons.
     */
    public AddMeetingCommand setConnectionToPerson(Set<Index> indices) {
        this.connectionToPerson.addAll(indices);
        return this;
    }

    /**
     * Get the connection indices to persons.
     */
    public Set<Index> getConnectionToPerson() {
        return this.connectionToPerson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        if (model.clashes(toAdd)) {
            List<Meeting> listOfClashingMeetings = model.getClashes(toAdd);
            String formatMeetingListString = CommandDisplayUtil.formatElementsIntoRows(listOfClashingMeetings);
            throw new CommandException(String.format(MESSAGE_CLASH_MEETING, formatMeetingListString));
        }

        addConnectionsToPersons(toAdd, model);

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
    /**
     * This method will handle the connections that the user wants to add from both the g/ and p/
     * Duplicate person that the user wants to build connection with this meeting will be automatically removed.
     */
    private void addConnectionsToPersons(Meeting toAdd, Model model) throws CommandException {
        // Use set to ensure unique element.
        HashSet<Person> personsConnection = new HashSet<>();
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());

        if (getConnectionToPerson().size() != 0) {
            List<Person> lastShownList = model.getFilteredPersonList();
            // Check whether the index is out of bounds
            for (Index index : getConnectionToPerson()) {
                if (index.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
            }
            // If we can pass the check, then add connection.
            for (Index index: getConnectionToPerson()) {
                Person personToAddConnection = lastShownList.get(index.getZeroBased());
                personsConnection.add(personToAddConnection);
            }
        }

        for (Person allPersonToAddConnection : personsConnection) {
            model.addPersonMeetingConnection(allPersonToAddConnection, toAdd);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}

