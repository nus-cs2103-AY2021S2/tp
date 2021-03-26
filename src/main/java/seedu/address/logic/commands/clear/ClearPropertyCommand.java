package seedu.address.logic.commands.clear;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;

/**
 * Clears the data in the property book.
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
