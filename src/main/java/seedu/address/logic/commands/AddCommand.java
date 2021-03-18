package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.garment.Garment;

/**
 * Adds a garment to the wardrobe.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a garment to the wardrobe. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_SIZE + "SIZE "
            + PREFIX_COLOUR + "COLOUR "
            + PREFIX_DRESSCODE + "DRESSCODE "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_SIZE + "45 "
            + PREFIX_COLOUR + "blue "
            + PREFIX_DRESSCODE + "casual "
            + PREFIX_DESCRIPTION + "friends "
            + PREFIX_DESCRIPTION + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New garment added: %1$s";
    public static final String MESSAGE_DUPLICATE_GARMENT = "This garment already exists in the wardrobe";

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
            throw new CommandException(MESSAGE_DUPLICATE_GARMENT);
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
