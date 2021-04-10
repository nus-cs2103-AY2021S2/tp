package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Mode;
import seedu.weeblingo.model.Model;

/**
 * Return to the menu from the different modes.
 */
public class EndCommand extends Command {

    public static final String COMMAND_WORD = "end";

    public static final String MESSAGE_SUCCESS = "Welcome back to the menu.\n"
            + "Enter \"learn\", \"quiz\" or \"history\" to enter different modes.";

    public static final String MESSAGE_END_IN_MENU = "Invalid 'end' command in menu mode.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int currentMode = model.getCurrentMode();

        if (currentMode == Mode.MODE_MENU) {
            throw new CommandException(MESSAGE_END_IN_MENU);
        }

        model.switchModeMenu();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

}
