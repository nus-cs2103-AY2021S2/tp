package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Sorts notes in the note list.
 */
public class SortNoteCommand extends Command {
    public static final String COMMAND_WORD = "sortnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all note the note list alphabetically.\n"
            + "Parameters: sortnote\n";

    public static final String MESSAGE_SORT_NOTE_SUCCESS = "Note sorted";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        model.sortNote();
        return new CommandResult(MESSAGE_SORT_NOTE_SUCCESS, UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

}
