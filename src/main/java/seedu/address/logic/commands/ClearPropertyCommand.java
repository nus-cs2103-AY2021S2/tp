package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the data in the property book.
 */
public class ClearPropertyCommand extends Command {

    public static final String COMMAND_WORD = "clear property";
    public static final String MESSAGE_SUCCESS = "Property book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearPropertyBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
