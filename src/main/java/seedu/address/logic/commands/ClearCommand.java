package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;

/**
 * Clears the projects & contacts list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Projects & Contacts data have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setColabFolder(new ColabFolder());
        return new CommandResult(MESSAGE_SUCCESS, new ShowTodayUiCommand());
    }
}
