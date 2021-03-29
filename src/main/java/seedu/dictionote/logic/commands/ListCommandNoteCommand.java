package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Format every available  command for display.
 */
public class ListCommandNoteCommand extends Command {

    public static final String COMMAND_WORD = "listcommandn";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Note Command Details (enter in the command for usage details)\n"
        + AddNoteCommand.COMMAND_WORD + " :  \n"
        + EditNoteCommand.COMMAND_WORD + " :  \n"
        + EditModeCommand.COMMAND_WORD + " : Enter Edit Mode to edit the shown note\n"
        + ShowNoteCommand.COMMAND_WORD + " :  \n"
        + DeleteNoteCommand.COMMAND_WORD + " :  \n"
        + ListNoteCommand.COMMAND_WORD + " :  \n"
        + SortNoteCommand.COMMAND_WORD + " :  \n"
        + SortNoteByTimeCommand.COMMAND_WORD + " :  \n"
        + MarkAsDoneNoteCommand.COMMAND_WORD + " :  ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
