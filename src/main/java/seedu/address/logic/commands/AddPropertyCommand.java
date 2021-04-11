package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.property.Deadline.MESSAGE_DEADLINE_OVER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.Property;

/**
 * Adds a property to the app.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "add property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to the app. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_POSTAL + "POSTAL "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_CLIENT_NAME + "CLIENT_NAME] "
            + "[" + PREFIX_CLIENT_CONTACT + "CLIENT_CONTACT] "
            + "[" + PREFIX_CLIENT_EMAIL + "CLIENT_EMAIL] "
            + "[" + PREFIX_CLIENT_ASKING_PRICE + "CLIENT_ASKING_PRICE] "
            + "[" + PREFIX_TAGS + "TAGS_SEPARATED_BY_COMMAS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Bishan "
            + PREFIX_TYPE + "Hdb "
            + PREFIX_ADDRESS + "Blk 150 Bishan Street 11 #02-101 "
            + PREFIX_POSTAL + "570150 "
            + PREFIX_DEADLINE + "30-06-2021 "
            + PREFIX_REMARK + "Urgent to sell "
            + PREFIX_CLIENT_NAME + "George "
            + PREFIX_CLIENT_CONTACT + "91124788 "
            + PREFIX_CLIENT_EMAIL + "george_4788@gmail.com "
            + PREFIX_CLIENT_ASKING_PRICE + "$750,000 "
            + PREFIX_TAGS + "Urgent, 4 bedrooms";

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
        requireNonNull(toAdd);

        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        Deadline deadline = toAdd.getDeadline();

        if (deadline.isOver()) {
            throw new CommandException(MESSAGE_DEADLINE_OVER);
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
