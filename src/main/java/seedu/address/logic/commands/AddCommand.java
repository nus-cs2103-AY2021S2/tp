package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.passenger.Passenger;

/**
 * Adds a passenger to the passenger list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a passenger to passenger list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_TRIPDAY + "DAY "
            + PREFIX_TRIPTIME + "TIME "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Jenny Talia "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_ADDRESS + "369, Yishun Ave 4, #02-25 "
            + PREFIX_TRIPDAY + "wednesday "
            + PREFIX_TRIPTIME + "1930 "
            + PREFIX_TAG + "female";

    public static final String MESSAGE_SUCCESS = "New passenger added: %1$s";
    public static final String MESSAGE_DUPLICATE_PASSENGER = "This passenger already exists in the GME Terminal";

    private final Passenger toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Passenger}
     */
    public AddCommand(Passenger passenger) {
        requireNonNull(passenger);
        toAdd = passenger;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPassenger(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PASSENGER);
        }

        model.addPassenger(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
