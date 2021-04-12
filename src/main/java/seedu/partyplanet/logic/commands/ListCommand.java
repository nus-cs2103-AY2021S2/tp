package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.date.Date;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

/**
 * Lists all persons in PartyPlanet to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons! ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists people in PartyPlanet "
            + "according to specified prefix combinations, with optional sort order.\n"
            + "Parameters: [--exact] [--any] [-n NAME]... [-t TAG]... [-b MONTH]... [-s SORT_FIELD] [-o SORT_ORDER]\n"
            + "Sort fields: 'n' (name, default), 'b' (birthday), 'u' (upcoming)\n"
            + "Sort orders: 'asc' (ascending, default), 'desc' (descending)\n"
            + "Example: list --exact -n alice -t friend -b 1 -s n -o desc\n";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD
            + " [--exact] [--any] [-n NAME]... [-t TAG]... [-b BIRTHDAY_MONTH]... [-s SORT_FIELD] [-o SORT_ORDER]";

    public static final Comparator<Person> SORT_NAME = Comparator.comparing(x -> x.getName().fullName.toLowerCase());
    public static final Comparator<Person> SORT_BIRTHDAY =
            Comparator.comparing(x -> Date.getDateWithoutYear(x.getBirthday()));
    public static final Comparator<Person> SORT_BIRTHDAY_UPCOMING =
            Comparator.comparing(x -> x.getBirthday().getDaysLeft(true));

    private final Comparator<Person> comparator;
    private final Predicate<Person> predicate;
    private String parseArguments;

    /**
     * Default empty ListCommand.
     * Shows the whole list.
     */
    public ListCommand() {
        this(PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * More general ListCommand accepting a single filtering predicate.
     * Default in ascending order, and the ANY flag is not applicable.
     */
    public ListCommand(Predicate<Person> predicate) {
        this(predicate, SORT_NAME, "");
    }

    /**
     * Most general ListCommand.
     *
     * @param predicate Predicate to filter people by.
     * @param comparator Sorting comparator.
     * @param parseArguments String containing information of listing requirements to be displayed.
     */
    public ListCommand(Predicate<Person> predicate, Comparator<Person> comparator, String parseArguments) {
        this.predicate = predicate;
        this.comparator = comparator;
        this.parseArguments = parseArguments;
    }

    /**
     * Returns the comparator used to sort the filtered list.
     * Getter implemented to expose {@code comparator} for testing.
     */
    public Comparator<Person> getComparator() {
        return comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(comparator);
        model.updateFilteredPersonList(predicate);
        String tagsRepresentation = displayTags(model.getFilteredPersonList())
                .replace("[", "").replace("]", "");
        if (model.getPersonListCopy().size() == model.getFilteredPersonList().size()) {
            return new CommandResult(ListCommand.MESSAGE_SUCCESS + parseArguments // No person filtered out
                    + String.format(Messages.MESSAGE_PERSONS_LISTED_TAGS, tagsRepresentation));
        }
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                    model.getFilteredPersonList().size())
                    + "Nobody met the requirements."
                    + String.format(Messages.MESSAGE_PERSONS_LISTED_TAGS, tagsRepresentation));
        }
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size())
                    + parseArguments
                    + String.format(Messages.MESSAGE_PERSONS_LISTED_TAGS, tagsRepresentation));
    }

    private String displayTags(List<Person> personsToDisplay) {
        Map<Tag, Integer> count = new HashMap<>();
        personsToDisplay.forEach(p -> p.getTags()
              .forEach(t -> count.compute(t, (k, v) -> v == null ? 1 : v + 1)));

        String output = count.entrySet().stream()
                .sorted((x, y) -> x.getKey().tagName.compareTo(y.getKey().tagName))
                .map(t -> String.format("%s (%d)", t.getKey(), t.getValue()))
                .reduce((x, y) -> x + ", " + y)
                .orElse("None!");

        return output;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ListCommand)) {
            return false;
        }
        ListCommand command = (ListCommand) other;
        return comparator.equals(command.comparator)
                && predicate.equals(((ListCommand) other).predicate); // state check
    }
}
