package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Adds an API endpoint to the API endpoint list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an API endpoint to the API endpoint list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New API endpoint added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENDPOINT = "This API endpoint already exists in the API endpoint list";

    private final Endpoint toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Endpoint}
     */
    public AddCommand(Endpoint endpoint) {
        requireNonNull(endpoint);
        toAdd = endpoint;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEndpoint(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENDPOINT);
        }

        model.addEndpoint(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
