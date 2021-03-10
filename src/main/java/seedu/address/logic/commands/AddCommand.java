package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Garment;

/**
 * Adds a garment to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a garment to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SIZE + "SIZE "
            + PREFIX_COLOUR + "COLOUR "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_SIZE + "45 "
            + PREFIX_COLOUR + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_DESCRIPTION + "friends "
            + PREFIX_DESCRIPTION + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New garment added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This garment already exists in the address book";

    private final Garment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Garment}
     */
    public AddCommand(Garment garment) {
        requireNonNull(garment);
        toAdd = garment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGarment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addGarment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
