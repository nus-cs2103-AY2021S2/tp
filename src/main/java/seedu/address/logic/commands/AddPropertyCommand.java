package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Adds a property to the app.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "add property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to the app. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_POSTAL + "POSTAL "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_REMARK + "REMARK "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Mayfair "
            + PREFIX_TYPE + "Condo "
            + PREFIX_ADDRESS + "Jurong East Street 32 "
            + PREFIX_POSTAL + "609477 "
            + PREFIX_DEADLINE + "31-12-2021 "
            + PREFIX_REMARK + "Urgent to sell";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the app";

    private final Property toAdd;

    /**
     * Creates an AddPropertyCommand to add the specified {@code Property}.
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        toAdd = property;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.addProperty(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyCommand // instanceof handles nulls
                && toAdd.equals(((AddPropertyCommand) other).toAdd));
    }
}
