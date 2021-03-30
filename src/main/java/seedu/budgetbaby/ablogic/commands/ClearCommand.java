package seedu.budgetbaby.ablogic.commands;

import static java.util.Objects.requireNonNull;

import seedu.budgetbaby.abmodel.AddressBook;
import seedu.budgetbaby.abmodel.Model;
import seedu.budgetbaby.logic.commands.CommandResult;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
