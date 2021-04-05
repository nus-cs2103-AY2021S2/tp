package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * Deletes an existing meeting from an existing person in the FriendDex.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "del-meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the meeting identified "
            + "by the second index number used, for the person identified by the first index number.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "1";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted meeting for %1$s";

    private final Index index;
    private final Index meetingIndex;

    /**
     * @param index of the person in the filtered person list to delete meeting from
     * @param meetingIndex of the meeting to be deleted
     */
    public DeleteMeetingCommand(Index index, Index meetingIndex) {
        requireAllNonNull(index, meetingIndex);

        this.index = index;
        this.meetingIndex = meetingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException((Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        List<Meeting> meetings = new ArrayList<>(personToEdit.getMeetings());
        if (meetingIndex.getZeroBased() >= meetings.size()) {
            throw new CommandException((Messages.MESSAGE_INVALID_INDEX_ARGUMENT));
        }
        meetings.remove(meetingIndex.getZeroBased());

        Person editedPerson = personToEdit.withMeetings(meetings);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        DeleteMeetingCommand e = (DeleteMeetingCommand) other;
        return index.equals(e.index) && meetingIndex.equals(e.meetingIndex);
    }
}
