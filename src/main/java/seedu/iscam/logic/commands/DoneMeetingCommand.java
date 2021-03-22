package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.meeting.Meeting;

/**
 * Completes an existing meeting in the iscam book.
 */
public class DoneMeetingCommand extends Command {

    public static final String COMMAND_WORD = "donemeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the meeting identified by the index number, used in displayed meeting list, as complete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Completed meeting: %1$s";
    public static final String MESSAGE_ALREADY_COMPLETE = "The specified meeting has already been completed.";

    private final Index index;

    /**
     * Completes a meeting specified by an index.
     */
    public DoneMeetingCommand(Index targetIndex) {
        this.index = targetIndex;
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meeting} but is completed.
     */
    private Meeting completeMeeting(Meeting meeting) {
        return new Meeting(meeting.getClientName(), meeting.getDateTime(), meeting.getLocation(),
                meeting.getDescription(), meeting.getTags(), true);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Meeting> meetings = model.getFilteredMeetingList();
        if (index.getZeroBased() >= meetings.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = meetings.get(index.getZeroBased());
        Meeting completedMeeting = completeMeeting(meeting);
        if (meeting.equals(completedMeeting)) {
            throw new CommandException(MESSAGE_ALREADY_COMPLETE);
        }

        model.setMeeting(meeting, completedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, completedMeeting));
    }
}
