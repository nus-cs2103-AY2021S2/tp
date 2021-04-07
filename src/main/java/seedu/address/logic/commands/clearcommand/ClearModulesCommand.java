package seedu.address.logic.commands.clearcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears the modules in RemindMe.
 */
public class ClearModulesCommand extends ClearCommand {

    public static final String MESSAGE_USAGE = "After m/, it should be empty!";

    public static final String MESSAGE_SUCCESS = "Modules have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetModules();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
