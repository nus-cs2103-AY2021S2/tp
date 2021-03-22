package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Edit the note in edit mode.
 */
public class EditModeNoteCommand extends Command {
    public static final String COMMAND_WORD = "editmode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the currently shown note."
            + " Note have to be showm using shownote command before entering edit mode";

    public static final String MESSAGE_EDIT_MODE_NOTE_SUCCESS = "Enter edit mode";
    public static final String MESSAGE_ALREADY_IN_EDIT_Mode = "Currently in edit mode.\n"
        + "type in save to save the edited content and quit edit mode\n"
        + "type in quit to quit edit mode without saving";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNoteShown()) {
            throw new CommandException(MESSAGE_USAGE);
        }
        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_ALREADY_IN_EDIT_Mode);
        }

        return new CommandResult(MESSAGE_EDIT_MODE_NOTE_SUCCESS,
                UiAction.EDITMODEENTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof EditModeNoteCommand; // instanceof handles nulls
    }
}
