package seedu.address.logic.commands.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class CustomerAddCommand extends Command {

    public static final String COMPONENT_WORD = "customer";
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Adds a customer to the customer list. \n"
            + "Parameters: "
            + PREFIX_NAME + "[NAME] "
            + PREFIX_PHONE + "[PHONE] "
            + PREFIX_EMAIL + "[EMAIL] "
            + PREFIX_ADDRESS + "[ADDRESS] "
            + "(" + PREFIX_TAG + "[TAG])...\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alan Tan "
            + PREFIX_PHONE + "81236789 "
            + PREFIX_EMAIL + "alantan@nus.edu.sg "
            + PREFIX_ADDRESS + "21 Lower Kent Ridge Road, Singapore 119077 "
            + PREFIX_TAG + "Gluten Allergy ";

    public static final String MESSAGE_SUCCESS = "New customer added: %1$s";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public CustomerAddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (CustomerCommandUtil.isValidCustomer(toAdd, model)) {
            model.addPerson(toAdd);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getName()),
                CommandResult.CRtype.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerAddCommand // instanceof handles nulls
                && toAdd.equals(((CustomerAddCommand) other).toAdd));
    }
}
