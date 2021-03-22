package seedu.iscam.logic.commands;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Location;
import seedu.iscam.model.meeting.Meeting;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;

public class RelocateMeetingCommand extends Command {

    public static final String COMMAND_WORD = "relocate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Relocate the meeting identified by the index number used in the displayed meeting list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LOCATION + " Starbucks, Bedok North\n";

    public static final String MESSAGE_SUCCESS = "Relocated meeting: %1$s";
    public static final String MESSAGE_DUPLICATE_LOCATION = "The new location must be different from the one in the " +
            "iscam book";

    private final Index index;
    private final Location location;

    public RelocateMeetingCommand(Index index, Location location) {
        this.index = index;
        this.location = location;
    }

    private Meeting relocateMeeting(Meeting meeting, Location newLocation) {
        return new Meeting(meeting.getClientName(), meeting.getDateTime(), newLocation,
                meeting.getDescription(), meeting.getTags(), meeting.getIsDone());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        if(index.getZeroBased() >= meetings.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = meetings.get(index.getZeroBased());
        Meeting relocatedMeeting = relocateMeeting(meeting, location);
        if (meeting.equals(relocatedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_LOCATION);
        }

        model.setMeeting(meeting, relocatedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, relocatedMeeting));
    }
}
