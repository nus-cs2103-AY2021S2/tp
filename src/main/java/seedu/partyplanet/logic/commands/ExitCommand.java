package seedu.partyplanet.logic.commands;

import seedu.partyplanet.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting PartyPlanet as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the application. ";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, true);
    }

}
