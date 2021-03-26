package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Format every available  command for display.
 */
public class ListCommandCommand extends Command {

    public static final String COMMAND_WORD = "listcommand";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_LIST_COMMAND_MESSAGE = "Dictionary Command List: \n"
        + FindContentCommand.COMMAND_WORD + ", "
        + FindDefinitionCommand.COMMAND_WORD + ", "
        + ListContentCommand.COMMAND_WORD + ", "
        + ListDefinitionCommand.COMMAND_WORD + ", "
        + ShowDictionaryContentCommand.COMMAND_WORD + "\n"
        + "Note Command List: \n"
        + AddNoteCommand.COMMAND_WORD + ", "
        + EditNoteCommand.COMMAND_WORD + ", "
        + ShowNoteCommand.COMMAND_WORD + ", "
        + DeleteNoteCommand.COMMAND_WORD + ", "
        + ListContactCommand.COMMAND_WORD + ", "
        + MarkAsDoneNoteCommand.COMMAND_WORD + "\n"
        + "Contact Command List: \n"
        + AddContactCommand.COMMAND_WORD + ", "
        + EditContactCommand.COMMAND_WORD + ", "
        + DeleteContactCommand.COMMAND_WORD + ", "
        + ListContactCommand.COMMAND_WORD + ", "
        + FindContactCommand.COMMAND_WORD + ", "
        + EmailContactCommand.COMMAND_WORD + "\n"
        + "Others Command List: \n"
        + OpenCommand.COMMAND_WORD + ", "
        + CloseCommand.COMMAND_WORD + ", "
        + HelpCommand.COMMAND_WORD + ", "
        + ExitCommand.COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
