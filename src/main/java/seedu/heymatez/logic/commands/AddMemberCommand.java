package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.person.Person;

/**
 * Adds a person to HeyMatez.
 */
public class AddMemberCommand extends Command {

    public static final String COMMAND_WORD = "addMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a member to HeyMatez. "
            + "Parameters: "
            + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[ " + PREFIX_ROLE + "ROLE ] \n"
            + "Example: " + COMMAND_WORD
            + " John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ROLE + "Vice president";

    public static final String MESSAGE_SUCCESS = "New Member Added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This member already exists in HeyMatez";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddMemberCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMemberCommand // instanceof handles nulls
                && toAdd.equals(((AddMemberCommand) other).toAdd));
    }
}
