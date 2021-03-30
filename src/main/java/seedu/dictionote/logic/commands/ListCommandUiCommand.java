package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Format every available  command for display.
 */
public class ListCommandUiCommand extends Command {

    public static final String COMMAND_WORD = "listcommandu";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "UI Command Details (enter in the command for usage details)\n"
        + OpenCommand.COMMAND_WORD + " : Open a specific UI Panel\n"
        + CloseCommand.COMMAND_WORD + " : Close a specific UI Panel\n"
        + SetDictionaryDividerPositionCommand.COMMAND_WORD + " : Set new position for dictionary divider\n"
        + SetNoteDividerPositionCommand.COMMAND_WORD + " : Set new position for note divider\n"
        + SetMainDividerPositionCommand.COMMAND_WORD + " : Set new position for main divider\n"
        + SetContactDividerPositionCommand.COMMAND_WORD + " : Set new position for contact divider\n"
        + ToggleDictionaryOrientationCommand.COMMAND_WORD + " : Change the Orientation of dictionary panel\n"
        + ToggleNoteOrientationCommand.COMMAND_WORD + " : Change the Orientation of note panel\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
