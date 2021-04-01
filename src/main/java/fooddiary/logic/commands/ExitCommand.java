package fooddiary.logic.commands;

import fooddiary.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Food Diary as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(null, null, MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, false, true);
    }

}
