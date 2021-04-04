package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Format every available  command for display.
 */
public class ListCommandCommand extends Command {

    public static final String COMMAND_WORD = "listcommand";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Dictionary Command List (enter `" + ListCommandDictionaryCommand.COMMAND_WORD + "` for more details)\n"
        + FindContentCommand.COMMAND_WORD + ", "
        + FindDefinitionCommand.COMMAND_WORD + ", "
        + ListContentCommand.COMMAND_WORD + ", "
        + ListDefinitionCommand.COMMAND_WORD + ", "
        + CopyContentToNoteCommand.COMMAND_WORD + ", "
        + ShowDictionaryContentCommand.COMMAND_WORD + "\n\n"
        + "Note Command List (enter `" + ListCommandNoteCommand.COMMAND_WORD + "` for more details)\n"
        + AddNoteCommand.COMMAND_WORD + ", "
        + EditNoteCommand.COMMAND_WORD + ", "
        + EditModeCommand.COMMAND_WORD + ", "
        + ShowNoteCommand.COMMAND_WORD + ", "
        + DeleteNoteCommand.COMMAND_WORD + ", "
        + ListNoteCommand.COMMAND_WORD + ", "
        + SortNoteCommand.COMMAND_WORD + ", "
        + SortNoteByTimeCommand.COMMAND_WORD + ", "
        + MarkAsDoneNoteCommand.COMMAND_WORD + ", "
        + MarkAsUndoneNoteCommand.COMMAND_WORD + ", "
        + MarkAllAsUndoneNoteCommand.COMMAND_WORD + ", "
        + ConvertTxtNoteCommand.COMMAND_WORD + ", "
        + EditModeQuitCommand.COMMAND_WORD + ", "
        + EditModeSaveCommand.COMMAND_WORD + ", "
        + FindNoteCommand.COMMAND_WORD + ", "
        + MergeNoteCommand.COMMAND_WORD + "\n\n"
        + "Contact Command List (enter `" + ListCommandContactCommand.COMMAND_WORD + "` for more details)\n"
        + AddContactCommand.COMMAND_WORD + ", "
        + EditContactCommand.COMMAND_WORD + ", "
        + DeleteContactCommand.COMMAND_WORD + ", "
        + ListContactCommand.COMMAND_WORD + ", "
        + FindContactCommand.COMMAND_WORD + ", "
        + EmailContactCommand.COMMAND_WORD + ", "
        + MostFreqContactCommand.COMMAND_WORD + ", "
        + ClearContactCommand.COMMAND_WORD + "\n\n"
        + "UI Command List: (enter `" + ListCommandUiCommand.COMMAND_WORD + "` for more details)\n"
        + OpenCommand.COMMAND_WORD + ", "
        + CloseCommand.COMMAND_WORD + ", "
        + SetDictionaryDividerPositionCommand.COMMAND_WORD + ", "
        + SetNoteDividerPositionCommand.COMMAND_WORD + ", "
        + SetMainDividerPositionCommand.COMMAND_WORD + ", "
        + SetContactDividerPositionCommand.COMMAND_WORD + ", "
        + ToggleDictionaryOrientationCommand.COMMAND_WORD + ", "
        + ToggleNoteOrientationCommand.COMMAND_WORD + "\n\n"
        + "Others Command List: \n"
        + HelpCommand.COMMAND_WORD + ", "
        + ExitCommand.COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
