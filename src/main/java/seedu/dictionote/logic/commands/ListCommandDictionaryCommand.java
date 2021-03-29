package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Format every available  command for display.
 */
public class ListCommandDictionaryCommand extends Command {

    public static final String COMMAND_WORD = "listcommandd";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Dictionary Command Details (enter in the command for usage details)\n"
        + FindContentCommand.COMMAND_WORD + " :  \n"
        + FindDefinitionCommand.COMMAND_WORD + " :  \n"
        + ListContentCommand.COMMAND_WORD + " :  \n"
        + ListDefinitionCommand.COMMAND_WORD + " :  \n"
        + ShowDictionaryContentCommand.COMMAND_WORD + " :  ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
