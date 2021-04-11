package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds meetings with that has all the "
            + "specified values.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TIME + "TIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + "[" + PREFIX_GROUP + "GROUP]..."
            + "[" + PREFIX_PERSON_CONNECTION + "INDEX OF PERSON RELATED]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103 Lecture "
            + PREFIX_TIME + "2021-03-12 14:00 "
            + PREFIX_DESCRIPTION + "Week 7 "
            + PREFIX_PRIORITY + "3 "
            + PREFIX_GROUP + "lectures "
            + PREFIX_GROUP + "SoC "
            + PREFIX_PERSON_CONNECTION + "1 "
            + PREFIX_PERSON_CONNECTION + "2";

    private final Predicate<Meeting> combinedPredicate;

    private final Set<Index> persons;

    /**
     * The constructor of find meeting command.
     */
    public FindMeetingCommand(Predicate<Meeting> combinedPredicate, Set<Index> personsIndexesToSearch) {
        super();
        this.combinedPredicate = combinedPredicate;
        persons = personsIndexesToSearch;
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
        Predicate<Meeting> containsPeoplePredicate = makeContainsPeoplePredicate(persons, model);
        Predicate<Meeting> finalPredicate = combinedPredicate.and(containsPeoplePredicate);
        model.updateFilteredMeetingList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }


    private Predicate<Meeting> makeContainsPeoplePredicate(Set<Index> people, Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Supplier<Stream<Integer>> zeroBasedIndexStreamSupplier = () ->
                people.stream().map(index -> index.getZeroBased());

        if (zeroBasedIndexStreamSupplier.get().anyMatch(integer -> integer
                >= lastShownList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSONS_DISPLAYED_INDEX);
        }

        Predicate<Meeting> personPred = meeting -> zeroBasedIndexStreamSupplier.get().allMatch(index ->
                meeting.containsPerson(lastShownList.get(index))
        );
        return personPred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindMeetingCommand that = (FindMeetingCommand) o;
        return persons.equals(that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons);
    }
}
