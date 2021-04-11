package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;

public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the note book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PRIORITY + "PRIORITY]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Complete CS2103 Tutorial "
            + PREFIX_PRIORITY + "5 \n"
            + "Default priority is 1";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the note book";

    private final Note toAdd;

    /**
     * Creates an AddNoteCommand to add the specified {@code Note}
     */
    public AddNoteCommand(Note note) {
        requireNonNull(note);
        this.toAdd = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNote(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNoteCommand // instanceof handles nulls
                && toAdd.equals(((AddNoteCommand) other).toAdd));
    }
}
