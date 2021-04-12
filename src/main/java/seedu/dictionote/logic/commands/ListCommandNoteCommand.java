package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Formats every available note command with description for display.
 */
public class ListCommandNoteCommand extends Command {

    public static final String COMMAND_WORD = "commanddetailn";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "Note Command Details (enter in the command for usage details)\n"
        + AddNoteCommand.COMMAND_WORD + " : Add a new note \n"
        + ListNoteCommand.COMMAND_WORD + " : List every existing note \n"
        + ShowNoteCommand.COMMAND_WORD + " : Show a specific note \n"
        + EditNoteCommand.COMMAND_WORD + " : Edit an existing note \n"
        + EditModeCommand.COMMAND_WORD + " : Enter Edit Mode to edit the shown note\n"
        + EditModeSaveCommand.COMMAND_WORD + " : Save the changes made in Edit Mode and also quits the Edit Mode \n"
        + EditModeQuitCommand.COMMAND_WORD + " : Exit Edit Mode \n"
        + FindNoteCommand.COMMAND_WORD + " : Find notes matching to a query \n"
        + DeleteNoteCommand.COMMAND_WORD + " : Delete an existing note  \n"
        + SortNoteCommand.COMMAND_WORD + " : Sort every note alphabetically  \n"
        + SortNoteByTimeCommand.COMMAND_WORD + " : Sort every note by the last edit time \n"
        + MarkAsDoneNoteCommand.COMMAND_WORD + " : Mark a note as done \n"
        + MarkAsUndoneNoteCommand.COMMAND_WORD + " : Mark a note as undone \n"
        + MarkAllAsUndoneNoteCommand.COMMAND_WORD + " : Mark all note as undone \n"
        + MergeNoteCommand.COMMAND_WORD + " : Merge two notes into one note \n"
        + ConvertTxtNoteCommand.COMMAND_WORD + " : Convert a note into a text file";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
