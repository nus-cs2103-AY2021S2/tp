package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.taskify.model.Model;
import seedu.taskify.model.Taskify;


/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Taskify has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new Taskify());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
