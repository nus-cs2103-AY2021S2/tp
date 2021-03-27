package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;



public class SortPersonCommand extends Command {

    private Comparator<Person> personComparator;

    public static final String COMMAND_WORD = "sortp";

    public SortPersonCommand(PersonSortOption sortOption, PersonSortDirection sortDirection) {
        switch (sortOption) {
        case NAME:
            personComparator = Comparator.comparing(person -> person.getName().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        }
        if (sortDirection == PersonSortDirection.DESC) {
            personComparator = personComparator.reversed();
        }

    }


    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredPersonList(this.personComparator);
        return new CommandResult("hey");
    }
}
