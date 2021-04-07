package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
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
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class FindMeetingCommand extends Command {

    public static final String COMMAND_WORD = "findm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": FIX THIS Finds all meetings with names containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Predicate<Meeting> combinedPredicate;

    private final Set<Index> persons;


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

        Supplier<Stream<Integer>> zeroBasedIndexStreamSupplier = () -> people.stream().map(index -> index.getZeroBased());

        if (zeroBasedIndexStreamSupplier.get().anyMatch(integer -> integer >=  lastShownList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSONS_DISPLAYED_INDEX);
        }

        Predicate<Meeting> personPred = meeting -> zeroBasedIndexStreamSupplier.get().allMatch(index ->
                meeting.containsPerson(lastShownList.get(index))
        );
        return personPred;
    }


}
