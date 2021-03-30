package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;

/**
 * Adds a note to the dictionote book.
 */
public class AddNoteCommand extends Command {
    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the dictionote book. "
            + "Parameters: "
            + PREFIX_CONTENT + "CONTENT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTENT + "I love you"
            + PREFIX_TAG + "CS2103 Test Tag";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the dictionote book";

    private final Note toAdd;

    /** Initializes a command to add the given note
     *
     * @param note
     */
    public AddNoteCommand(Note note) {
        requireNonNull(note);
        toAdd = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.onEditModeNote()) {
            throw new CommandException(MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
        }

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), UiAction.OPEN, UiActionOption.NOTE_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteCommand) other).toAdd));
    }

}
