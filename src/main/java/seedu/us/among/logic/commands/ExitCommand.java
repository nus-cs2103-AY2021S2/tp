package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandResult.exitCommandResult;

import seedu.us.among.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting imPoster as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return exitCommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
