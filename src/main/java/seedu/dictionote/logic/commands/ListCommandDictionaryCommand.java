package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Formats every available dictionary command with description for display.
 */
public class ListCommandDictionaryCommand extends Command {

    public static final String COMMAND_WORD = "commanddetaild";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Dictionary Command Details (enter in the command for usage details)\n"
        + FindContentCommand.COMMAND_WORD + " : Find content matching the specific keyword(s). \n"
        + FindDefinitionCommand.COMMAND_WORD + " : Find definition(s) matching the specific keyword(s). \n"
        + ListContentCommand.COMMAND_WORD + " :  List all content in the Dictionary. \n"
        + ListDefinitionCommand.COMMAND_WORD + " :  List all the definitions in the Dictionary. \n"
        + CopyContentToNoteCommand.COMMAND_WORD + " : Copy the content at the given index from the Dictionary's"
                + " content list and creates a note using the content. \n"
        + ShowDictionaryContentCommand.COMMAND_WORD + " : Show the content at the given index from the Dictionary's"
                + " content list. \n"
        + AddDefinitionCommand.COMMAND_WORD + " : Adds a new definition in the Dictionary. \n"
        + AddContentCommand.COMMAND_WORD + " : Adds a new content in the Dictionary. \n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
