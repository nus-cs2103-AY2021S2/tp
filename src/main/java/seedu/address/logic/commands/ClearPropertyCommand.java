package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.PropertyBook;

/**
 * Clears the sample data in the property book.
 */
public class ClearPropertyCommand extends Command {

    public static final String COMMAND_WORD = "clear property";
    public static final String MESSAGE_SUCCESS = "Property book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPropertyBook(new PropertyBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
