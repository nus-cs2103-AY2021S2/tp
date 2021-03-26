package seedu.us.among.logic.commands;

import seedu.us.among.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting imPoster as requested ...";

    public static final boolean showHelp = false;
    public static final boolean isExit = true;
    public static final boolean isList = false;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, showHelp, isExit, isList);
    }

}
