package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;

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

        // Get Meeting list from Model

        // Throw exception if index is out of range

        // Get Client from list using targetIndex

        // Delete Client from Model

        return new CommandResult("PLACEHOLDER DELETE SUCCESS");
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteMeetingCommand
                && targetIndex.equals(((DeleteMeetingCommand) other).targetIndex));
    }
}
