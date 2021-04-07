package seedu.address.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the RemindMe.
 */
public class AddPersonCommand extends AddCommand {

    public static final String MESSAGE_USAGE =
            "Missing necessary prefixes: n/ and b/\n"
            + "Person: add n/NAME b/BIRTHDAY [t/TAG...]\n"
            + "Example: add n/Bob b/19/12/2000 t/friend";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the RemindMe";

    private final Person toAdd;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Person person) {
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
                || (other instanceof AddPersonCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonCommand) other).toAdd));
    }
}
