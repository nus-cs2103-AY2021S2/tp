package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
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

    public static final String MESSAGE_PERSON_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_EVENT_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the event book";

    private final Person toAddPerson;
    private final Event toAddEvent;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAddPerson = person;
        toAddEvent = null;
    }


    /**
     * Creates an AddCommand to add the specified {@code Event}
     */
    /* Currently commenting this out since it causes a null error in the Tests
    public AddCommand(Event event) {
        requireNonNull(event);
        toAddEvent = event;
        toAddPerson = null;
    }
    */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (toAddEvent == null) {
            if (model.hasPerson(toAddPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.addPerson(toAddPerson);
            return new CommandResult(String.format(MESSAGE_PERSON_SUCCESS, toAddPerson));
        } else {
            if (model.hasEvent(toAddEvent)) {
                throw new CommandException(MESSAGE_DUPLICATE_EVENT);
            }
            model.addEvent(toAddEvent);
            return new CommandResult(String.format(MESSAGE_EVENT_SUCCESS, toAddEvent));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAddPerson.equals(((AddCommand) other).toAddPerson));
    }
}
