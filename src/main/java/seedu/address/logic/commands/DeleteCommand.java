package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.person.Name;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from Car Tinder.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the \n"
            + "Parameters: Customer name\n"
            + "Example: " + COMMAND_WORD + " Alex Peng";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final String targetName;

    public DeleteCommand(String name) {
        this.targetName = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<String> names = new ArrayList<>();

        for (Person p : lastShownList) {
            names.add(p.getName().toString());
        }

        if (!names.contains(targetName)) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_NAME_IN_BOOK);
        }

        Person personToDelete = lastShownList.get(names.indexOf(targetName));
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetName.equals(((DeleteCommand) other).targetName)); // state check
    }
}
