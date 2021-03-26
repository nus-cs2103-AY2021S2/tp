package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;

/**
 * Adds a person to PartyPlanet.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to PartyPlanet. "
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_TAG + " TAG]... "
            + "[" + PREFIX_BIRTHDAY + " BIRTHDAY] "
            + "[" + PREFIX_REMARK + " REMARK] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " johnd@example.com "
            + PREFIX_ADDRESS + " 311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + " friends "
            + PREFIX_TAG + " owesMoney "
            + PREFIX_BIRTHDAY + " 1999-06-01 "
            + PREFIX_REMARK + " hates vegetables";

    public static final String MESSAGE_USAGE_CONCISE =
            COMMAND_WORD + " -n NAME [-p PHONE] [-e EMAIL] [-a ADDRESS] [-t TAG]... [-b BIRTHDAY] [-r REMARK]";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in PartyPlanet";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
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
        model.addState();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
