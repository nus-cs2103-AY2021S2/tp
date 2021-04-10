package seedu.address.logic.commands.resident;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.resident.Resident;

/**
 * Adds a resident to the address book.
 */
public class AddResidentCommand extends Command {

    public static final String COMMAND_WORD = "radd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a resident to SunRez.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_YEAR + "YEAR\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_YEAR + "1";

    public static final String MESSAGE_SUCCESS = "New resident added: %1$s";
    public static final String MESSAGE_DUPLICATE_RESIDENT = "This resident already exists in SunRez.";
    private static final Logger logger = LogsCenter.getLogger(AddResidentCommand.class);

    private final Resident toAdd;

    /**
     * Creates an AddResidentCommand to add the specified {@code Resident}
     */
    public AddResidentCommand(Resident resident) {
        requireNonNull(resident);
        toAdd = resident;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasResident(toAdd)) {
            logger.warning("Duplicate resident added to radd command");
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENT);
        }

        model.addResident(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddResidentCommand // instanceof handles nulls
                && toAdd.equals(((AddResidentCommand) other).toAdd));
    }
}
