package seedu.heymatez.logic.commands;

import seedu.heymatez.model.Model;

/**
 * Terminates HeyMatez.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting HEY MATEz as requested ... Goodbye";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
