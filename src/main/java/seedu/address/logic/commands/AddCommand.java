package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residence.Residence;

/**
 * Adds a residence to the residence tracker.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a residence to the residence tracker.\n"
            + "Parameters: "
            + PREFIX_RESIDENCE_NAME + "NAME "
            + PREFIX_RESIDENCE_ADDRESS + "ADDRESS "
            + PREFIX_BOOKING_DETAILS + "BOOKING DETAILS "
            + "[" + PREFIX_CLEAN_STATUS_TAG + "y or n]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RESIDENCE_NAME + "Seaside Villa "
            + PREFIX_RESIDENCE_ADDRESS + "311, Pasir Ris Ave 2, #02-25 "
            + PREFIX_CLEAN_STATUS_TAG + "n"
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_SUCCESS = "New residence added: %1$s";
    public static final String MESSAGE_DUPLICATE_RESIDENCE = "This residence already exists in the residence tracker";

    private final Residence toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Residence}
     */
    public AddCommand(Residence residence) {
        requireNonNull(residence);
        toAdd = residence;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasResidence(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENCE);
        }

        model.addResidence(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

}
