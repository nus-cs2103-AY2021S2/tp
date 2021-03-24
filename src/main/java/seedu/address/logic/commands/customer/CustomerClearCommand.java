package seedu.address.logic.commands.customer;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.PersonBook;

/**
 * Clears the address book.
 */
public class CustomerClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new PersonBook());
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CRtype.PERSON);
    }
}
