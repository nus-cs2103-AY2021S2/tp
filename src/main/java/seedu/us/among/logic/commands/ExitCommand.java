package seedu.us.among.logic.commands;

import seedu.us.among.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final boolean SHOW_HELP = false;
    public static final boolean IS_EXIT = true;
    public static final boolean IS_LIST = false;


    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting imPoster as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, SHOW_HELP, IS_EXIT, IS_LIST);
    }

}
