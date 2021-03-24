package seedu.weeblingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;

/**
 * Ends the current quiz session and returns the user to the original start page.
 */
public class EndCommand extends Command {

    public static final String COMMAND_WORD = "end";

    public static final String MESSAGE_SUCCESS = "Welcome back.\n"
            + "Enter \"learn\" or \"quiz\" for different modes.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearQuizInstance();
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false);
    }

}
