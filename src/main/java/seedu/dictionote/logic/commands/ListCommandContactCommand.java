package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Formats every available contact command with description for display.
 */
public class ListCommandContactCommand extends Command {

    public static final String COMMAND_WORD = "commanddetailc";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Contact Command Details (enter in the command for usage details)\n"
        + AddContactCommand.COMMAND_WORD + " : Add a new contact to the contacts list. \n"
        + ListContactCommand.COMMAND_WORD + " : List all contacts in the contacts list. \n"
        + EditContactCommand.COMMAND_WORD + " : Edit an existing contact in the contacts list.  \n"
        + FindContactCommand.COMMAND_WORD + " : Find the contacts list by name, email, and/or tags. \n"
        + DeleteContactCommand.COMMAND_WORD + " : Delete an existing contact from the contacts list.  \n"
        + EmailContactCommand.COMMAND_WORD + " : Send an email to an existing contact in the contacts list. \n"
        + MostFreqContactCommand.COMMAND_WORD + " : Sort the contacts list by most-frequently contacted. \n"
        + ClearContactCommand.COMMAND_WORD + " : Remove all existing contacts in the contacts list. ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
