package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_IMAGE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;

/**
 * Adds a client to the iscam book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to the iScam Book. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_LOCATION + "LOCATION "
            + "[" + PREFIX_PLAN + "INSURANCE_PLAN] "
            + "[" + PREFIX_IMAGE + "IMAGE_FILE] "
            + "[" + PREFIX_TAG + "TAG(s)]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_LOCATION + "01 Singapore Street, #23-45 "
            + PREFIX_PLAN + "MediShield Life "
            + PREFIX_IMAGE + "john_doe.png "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the iScam book";

    private final Client toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     * @param client client to be added
     */
    public AddCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
