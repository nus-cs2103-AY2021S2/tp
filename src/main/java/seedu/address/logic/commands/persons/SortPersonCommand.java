package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    private static final List<String> all_options = Arrays.stream(PersonSortOption.values()).
            map(personSortOption ->
                    personSortOption.getValue()).collect(Collectors.toList());
    private static final List<String> all_directions = Arrays.stream(PersonSortDirection.values()).
            map(PersonSortDirection ->
                    PersonSortDirection.getValue()).collect(Collectors.toList());

    public static final String MESSAGE_USAGE = "Please do: " + COMMAND_WORD + " " +
            PREFIX_SORT_BY + all_options.toString() + " " +
            PREFIX_SORT_DIRECTION + all_directions.toString();

    public SortPersonCommand(PersonSortOption sortOption, PersonSortDirection sortDirection) {
        switch (sortOption) {
        case NAME:
            personComparator = Comparator.comparing(person -> person.getName().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        case EMAIL:
            personComparator = Comparator.comparing(person -> person.getEmail().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        case PHONE:
            personComparator = Comparator.comparing(person -> person.getPhone().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        case ADDRESS:
            personComparator = Comparator.comparing(person -> person.getAddress().toString(),
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
        return new CommandResult("Sorted");
    }
}
