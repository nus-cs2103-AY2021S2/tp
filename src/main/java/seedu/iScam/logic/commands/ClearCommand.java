package seedu.iScam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iScam.model.ClientBook;
import seedu.iScam.model.Model;

/**
 * Clears the iScam book.
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
