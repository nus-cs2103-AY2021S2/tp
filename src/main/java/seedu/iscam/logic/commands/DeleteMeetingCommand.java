package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.meeting.Meeting;

/**
 * Deletes a meeting identified using it's displayed index from the iscam book's interface.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deletemeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the meeting identified by the index number used in displayed meeting list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted Meeting: %1$s";

    private final Index targetIndex;

    public DeleteMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteMeeting(meetingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteMeetingCommand
                && targetIndex.equals(((DeleteMeetingCommand) other).targetIndex));
    }
}
