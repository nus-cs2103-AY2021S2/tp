package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutor.Tutor;

/**
 * Adds a tutor to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add_tutor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutor to the tutor list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[<" + PREFIX_SUBJECT_NAME + "SUBJECT_NAME"
            + " " + PREFIX_EDUCATION_LEVEL + "EDUCATION_LEVEL"
            + " " + PREFIX_RATE + "RATE"
            + " " + PREFIX_YEAR + "YEARS EXPERIENCE"
            + " " + PREFIX_QUALIFICATION + "QUALIFICATION>]..."
            + " [" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GENDER + "Male "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SUBJECT_NAME + "English "
            + PREFIX_RATE + "50 "
            + PREFIX_EDUCATION_LEVEL + "Sec 3 "
            + PREFIX_YEAR + "5 "
            + PREFIX_QUALIFICATION + "A-Level ";

    public static final String MESSAGE_SUCCESS = "New tutor added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This tutor already exists "
            + "in the address book";

    private final Tutor toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Tutor tutor) {
        requireNonNull(tutor);
        toAdd = tutor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addTutor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
