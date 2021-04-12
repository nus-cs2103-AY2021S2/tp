package seedu.dictionote.logic.commands;

import seedu.dictionote.model.Model;

/**
 * Formats every available UI command with description for display.
 */
public class ListCommandUiCommand extends Command {

    public static final String COMMAND_WORD = "commanddetailu";

    public static final String SHOWING_LIST_COMMAND_MESSAGE =
        "UI Command Details (enter in the command for usage details)\n"
        + OpenCommand.COMMAND_WORD + " : Open a specific UI Panel\n"
        + CloseCommand.COMMAND_WORD + " : Close a specific UI Panel\n"
        + SetContactDividerPositionCommand.COMMAND_WORD + " : Set new position for contact divider\n"
        + SetDictionaryDividerPositionCommand.COMMAND_WORD + " : Set new position for dictionary divider\n"
        + SetNoteDividerPositionCommand.COMMAND_WORD + " : Set new position for note divider\n"
        + SetMainDividerPositionCommand.COMMAND_WORD + " : Set new position for main divider\n"
        + ToggleDictionaryOrientationCommand.COMMAND_WORD + " : Toggle the Orientation of dictionary panel\n"
        + ToggleNoteOrientationCommand.COMMAND_WORD + " : Toggle the Orientation of note panel";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
    }
}
