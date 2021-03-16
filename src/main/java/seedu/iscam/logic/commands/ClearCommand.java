package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.model.ClientBook;
import seedu.iscam.model.Model;

/**
 * Clears the iscam book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Location book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClientBook(new ClientBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
