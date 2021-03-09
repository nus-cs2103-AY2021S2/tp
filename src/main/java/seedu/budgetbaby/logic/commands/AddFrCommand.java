package seedu.budgetbaby.logic.commands;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.Model;
import seedu.budgetbaby.model.record.FinancialRecord;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Adds a financial record to the budget tracker.
 */
public class AddFrCommand extends Command {

    public static final String COMMAND_WORD = "add-fr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a financial record. "
        + "Parameters: "
        + PREFIX_DESCRIPTION + "DESCRIPTION "
        + PREFIX_AMOUNT + "AMOUNT "
//        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_DESCRIPTION + "Lunch "
        + PREFIX_AMOUNT + "10";
//        + PREFIX_TAG + "friends "
//        + PREFIX_TAG + "owesMoney"

    public static final String MESSAGE_SUCCESS = "New financial record added: %1$s";
//    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final FinancialRecord toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddFrCommand(FinancialRecord record) {
        requireNonNull(record);
        toAdd = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        if (model.hasPerson(toAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
//        }
//
//        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddFrCommand // instanceof handles nulls
            && toAdd.equals(((AddFrCommand) other).toAdd));
    }
}