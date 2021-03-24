package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_READER;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.reader.Reader;

/**
 * Adds a reader to the registered reader base.
 */
public class AddReaderCommand extends Command {

    public static final String COMMAND_WORD = "addreader";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reader to the registered reader base. "
            + "Parameters: "
            + PREFIX_READER + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_READER + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "1-year-membership "
            + PREFIX_TAG + "vip";

    public static final String MESSAGE_SUCCESS = "New reader added: %1$s";
    public static final String MESSAGE_DUPLICATE_READER = "This reader already exists in the registered reader base";

    private final Reader toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Reader}
     */
    public AddReaderCommand(Reader reader) {
        requireNonNull(reader);
        toAdd = reader;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasReader(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_READER);
        }

        model.addReader(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReaderCommand // instanceof handles nulls
                && toAdd.equals(((AddReaderCommand) other).toAdd));
    }
}
