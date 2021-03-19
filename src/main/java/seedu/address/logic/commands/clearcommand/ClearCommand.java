package seedu.address.logic.commands.clearcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.RemindMe;

/**
 * Clears RemindMe.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": clears RemindMe. "
            + "Parameters: " + "Example: " + COMMAND_WORD + "\n"
            + ClearPersonsCommand.MESSAGE_USAGE + "\n"
            + ClearModulesCommand.MESSAGE_USAGE;

    public static final String MESSAGE_SUCCESS = "RemindMe is cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRemindMe(new RemindMe());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
