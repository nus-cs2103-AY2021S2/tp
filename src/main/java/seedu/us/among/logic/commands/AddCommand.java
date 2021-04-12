package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.core.Messages.MESSAGE_USE_HELP;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Adds an API endpoint to the API endpoint list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_API_EXAMPLE = COMMAND_WORD + " "
            + PREFIX_METHOD + " PUT "
            + PREFIX_ADDRESS + " https://reqres.in/api/users/2 "
            + PREFIX_DATA + " {\"name\": \"steve\", \"job\": \"lawyer\"} "
            + PREFIX_HEADER + " \"key: value\" "
            + PREFIX_TAG + " important "
            + PREFIX_TAG + " test\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an endpoint to the endpoint list.\n"
            + "Parameters: "
            + PREFIX_METHOD + " METHOD "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_DATA + " DATA "
            + "[" + PREFIX_HEADER + " HEADER] "
            + "[" + PREFIX_TAG + " TAG]\n"
            + MESSAGE_USE_HELP + "\n\n"
            + "Example: "
            + MESSAGE_API_EXAMPLE;

    public static final String MESSAGE_SUCCESS = "Endpoint added: \n%1$s";
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), toAdd, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }
}
