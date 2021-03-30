package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;

/**
 * Clears the contact list.
 */
public class ClearContactCommand extends Command {

    public static final String COMMAND_WORD = "clearC";
    public static final String MESSAGE_SUCCESS = "Contacts have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setColabFolder(new ColabFolder());
        return new CommandResult(MESSAGE_SUCCESS, new ShowTodayUiCommand());
    }
}
