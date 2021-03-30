package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens up a schedule window displaying weekly lessons for the user.
 */
public class ImportantDatesCommand extends Command {

    public static final String COMMAND_WORD = "idates";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows weekly lesson schedule.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened schedule window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, true, false, false);
    }
}
