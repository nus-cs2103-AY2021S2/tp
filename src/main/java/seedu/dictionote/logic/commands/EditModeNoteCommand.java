package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Edit the note in edit mode.
 */
public class EditModeNoteCommand extends Command {
    public static final String COMMAND_WORD = "editmodenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the current shown note."
            + " A note have to be showm using shownote command before entering edit mode\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_EDIT_MODE_NOTE_SUCCESS = "Enter edit mode";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNoteShown()) {
            throw new CommandException(Messages.MESSAGE_NO_NOTE_SHOWN);
        }

        return new CommandResult(MESSAGE_EDIT_MODE_NOTE_SUCCESS,
                UiAction.ENTEREDITMODE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof EditModeNoteCommand; // instanceof handles nulls
    }
}
