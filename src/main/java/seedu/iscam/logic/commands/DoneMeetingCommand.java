package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;

public class DoneMeetingCommand extends Command {

    public static final String COMMAND_WORD = "donemeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the meeting identified by the index number, used in displayed meeting list, as complete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index index;

    public DoneMeetingCommand(Index targetIndex) {
        this.index = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptor();
        descriptor.setIsDone(true);
        return new EditMeetingCommand(index, descriptor).execute(model);
    }
}
