package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using its displayed name in HeyMatez.
 */
public class DeleteMemberCommand extends Command {

    public static final String COMMAND_WORD = "deleteMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the member identified by the exact name (case-sensitive) used in the displayed members list.\n"
            + "Parameters: NAME (must be a valid name)\n"
            + "Example: " + COMMAND_WORD + " John";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Member: %1$s";

    private final Name name;

    public DeleteMemberCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToDelete = null;

        for (Person person : lastShownList) {
            Name currentName = person.getName();

            if (name.equals(currentName)) {
                personToDelete = person;
                break;
            }
        }

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        model.deletePerson(personToDelete);
        model.removeAssignee(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMemberCommand // instanceof handles nulls
                && name.equals(((DeleteMemberCommand) other).name)); // state check
    }
}
