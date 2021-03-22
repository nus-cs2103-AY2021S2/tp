package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_ON;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Meeting;

/**
 * Reschedules the date and time of a meeting in the iscam book.
 */
public class RescheduleMeetingCommand extends Command {

    public static final String COMMAND_WORD = "reschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reschedule the date and time for a meeting identified by the index number used in displayed meeting "
            + "list. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_ON + "DATE-TIME (must not be in the past)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ON + "12-10-2020 16:30\n";

    public static final String MESSAGE_SUCCESS = "Rescheduled meeting: %1$s";
    public static final String MESSAGE_DUPLICATE_DATETIME = "The new date and time must be different from the " +
            "original.";

    private final Index index;
    private final DateTime dateTime;

    /**
     * Creates a RescheduleMeetingCommand to change the {@code DateTime} for a meeting specified by {@code Index}
     */
    public RescheduleMeetingCommand(Index index, DateTime dateTime) {
        requireNonNull(dateTime);
        this.index = index;
        this.dateTime = dateTime;
    }

    private Meeting rescheduleMeeting(Meeting meeting, DateTime newDateTime) {
        return new Meeting(meeting.getClientName(), newDateTime, meeting.getLocation(),
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
        Meeting rescheduledMeeting = rescheduleMeeting(meeting, dateTime);
        if (meeting.equals(rescheduledMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_DATETIME);
        }

        model.setMeeting(meeting, rescheduledMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, rescheduledMeeting));
    }
}
