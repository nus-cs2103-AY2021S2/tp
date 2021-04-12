package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;

/**
 * Deletes a person identified using its email in the booking system.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delete_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the email used in the displayed person list.\n"
            + "Parameters: e/EMAIL\n"
            + "Example: " + COMMAND_WORD + " e/pam@example.com";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Email email;

    public DeletePersonCommand(Email email) {
        this.email = email;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (!lastShownList.stream().anyMatch(email::isSameEmail)) {
            throw new CommandException(Messages.MESSAGE_NON_EXISTENT_PERSON_EMAIL);
        }

        Person personToDelete = lastShownList.stream().filter(email::isSameEmail).findFirst().orElse(null);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && email.equals(((DeletePersonCommand) other).email)); // state check
    }
}
