package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;

import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.Note;



public class AddNoteCommand extends Command {
    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the dictionote book. "
            + "Parameters: "
            + PREFIX_CONTENT + "CONTENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTENT + "I love you";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This note already exists in the dictionote book";

    private final Note toAdd;

    /** Add a note
     *
     * @param note
     */

    public AddNoteCommand(Note note) {
        requireNonNull(note);
        toAdd = note;
    }


    /** Executes the command
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException if the command has wrong format
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNote(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
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
