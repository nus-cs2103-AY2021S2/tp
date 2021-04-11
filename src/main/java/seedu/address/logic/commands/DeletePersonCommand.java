package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID_STUDENT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;

/**
 * Deletes a person identified using the person ID from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delete_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the person ID shown in the displayed person list.\n"
            + "Parameters: PERSON_ID (must be a valid ID)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PERSON_ID_STUDENT + "1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final PersonId targetPersonId;

    public DeletePersonCommand(PersonId targetPersonId) {
        this.targetPersonId = targetPersonId;
    }

    public PersonId getTargetPersonId() {
        return targetPersonId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getUnfilteredPersonList();

        Optional<Person> personToDelete = lastShownList.stream()
                .filter(x-> x.getPersonId().equals(targetPersonId)).findAny();

        if (!personToDelete.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (personToDelete.get().hasSession()) {
            throw new CommandException(Messages.MESSAGE_CANNOT_DELETE_PERSON);
        }

        model.deletePerson(personToDelete.get());
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetPersonId.equals(((DeletePersonCommand) other).targetPersonId)); // state check
    }
}
