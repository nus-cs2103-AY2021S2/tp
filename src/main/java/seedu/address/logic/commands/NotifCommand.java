package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;

/**
 * Shows notifications the program.
 */
public class NotifCommand extends Command {

    public static final String COMMAND_WORD = "notif";

    public static final String SHOWING_NOTIF_MESSAGE = "Producing notifications...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_NOTIF_MESSAGE, false, true, Index.fromOneBased(0), false);
    }

}
