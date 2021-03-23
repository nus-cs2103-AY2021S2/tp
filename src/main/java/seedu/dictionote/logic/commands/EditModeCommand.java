package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Edit the note in edit mode.
 */
public class EditModeCommand extends Command {
    public static final String COMMAND_WORD = "editmode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Enter Edit Mode to edit the current note."
            + " use `shownote` command to show the note for editing";

    public static final String MESSAGE_EDIT_MODE_NOTE_SUCCESS = "Enter edit mode";
    public static final String MESSAGE_ALREADY_IN_EDIT_MODE = "Currently in edit mode.\n"
        + "type in `save` to save the edited content and quit edit mode\n"
        + "type in `quit` to quit edit mode without saving";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNoteShown()) {
            throw new CommandException(MESSAGE_USAGE);
        }
        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_ALREADY_IN_EDIT_MODE);
        }

        return new CommandResult(MESSAGE_EDIT_MODE_NOTE_SUCCESS,
                UiAction.EDITMODEENTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof EditModeCommand; // instanceof handles nulls
    }
}
