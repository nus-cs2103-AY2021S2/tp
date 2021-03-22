package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Edit the note in edit mode.
 */
public class EditModeExitCommand extends Command {
    public static final String COMMAND_WORD = "editmodeexit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the current shown note."
            + " A note have to be showm using shownote command before entering edit mode\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_EDIT_MODE_EXIT_SUCCESS = "Exit edit mode.";
    public static final String MESSAGE_NOT_IN_EDIT_MODE = "Not in edit mode.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.onEditModeNote()) {
            throw new CommandException(MESSAGE_NOT_IN_EDIT_MODE);
        }

        model.resetNoteShown();

        return new CommandResult(MESSAGE_EDIT_MODE_EXIT_SUCCESS,
                UiAction.EXITEDITMODE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof EditModeExitCommand; // instanceof handles nulls
    }
}
