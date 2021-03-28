package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Format every available  command for display.
 */
public class ListCommandContactCommand extends Command {

    public static final String COMMAND_WORD = "listcommandd";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Contact Command Details (enter in the command for usage details)"
        + AddContactCommand.COMMAND_WORD + " :  \n"
        + EditContactCommand.COMMAND_WORD + " :  \n"
        + DeleteContactCommand.COMMAND_WORD + " :  \n"
        + ListContactCommand.COMMAND_WORD + " :  \n"
        + FindContactCommand.COMMAND_WORD + " :  \n"
        + EmailContactCommand.COMMAND_WORD + " :  ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
