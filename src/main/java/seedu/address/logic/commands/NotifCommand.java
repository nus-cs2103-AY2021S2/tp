package seedu.address.logic.commands;

import java.util.Optional;

import seedu.address.model.Model;

/**
 * Shows notifications the program.
 */
public class NotifCommand extends Command {

    public static final String COMMAND_WORD = "notif";

    public static final String SHOWING_NOTIF_MESSAGE = "Producing notifications...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_NOTIF_MESSAGE, false, true, Optional.empty(), false);
    }

}
