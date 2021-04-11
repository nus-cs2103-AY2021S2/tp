package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;



public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sortp";

    public static final List<String> ALL_OPTIONS = Arrays.stream(PersonSortOption.values())
            .map(personSortOption -> personSortOption.getValue()).collect(Collectors.toList());
    public static final List<String> ALL_DIRECTIONS = Arrays.stream(PersonSortDirection.values())
            .map(PersonSortDirection -> PersonSortDirection.getValue()).collect(Collectors.toList());

    public static final String MESSAGE_USAGE = "Please do: " + COMMAND_WORD + " "
            + PREFIX_SORT_BY + ALL_OPTIONS.toString() + " "
            + PREFIX_SORT_DIRECTION + ALL_DIRECTIONS.toString();
    private Comparator<Person> personComparator;

    private PersonSortOption personSortOption;
    private PersonSortDirection personSortDirection;
    /**
     * Construction for sort person command.
     */
    public SortPersonCommand(PersonSortOption sortOption, PersonSortDirection sortDirection) {
        personSortOption = sortOption;
        personSortDirection = sortDirection;

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
        default:
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortPersonCommand that = (SortPersonCommand) o;
        return personSortOption == that.personSortOption && personSortDirection == that.personSortDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personSortOption, personSortDirection);
    }
}
