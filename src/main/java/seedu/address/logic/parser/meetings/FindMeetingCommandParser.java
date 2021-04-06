package seedu.address.logic.parser.meetings;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.meetings.FindMeetingCommand;
import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;


public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    List<Predicate<Meeting>> allPredicates = new ArrayList<Predicate<Meeting>>();;

    /**
     * Parses {@code args} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public FindMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_CONNECTION,
                        PREFIX_NAME, PREFIX_TIME, PREFIX_DESCRIPTION,
                        PREFIX_PRIORITY);

        List<String> personIndexes = argMultimap.getAllValues(PREFIX_PERSON_CONNECTION);
        List<String> meetingNames = argMultimap.getAllValues(PREFIX_NAME);
        List<String> meetingTimes = argMultimap.getAllValues(PREFIX_TIME);
        List<String> meetingDescriptions = argMultimap.getAllValues(PREFIX_DESCRIPTION);
        List<String> meetingPriorities = argMultimap.getAllValues(PREFIX_DESCRIPTION);

        try {
            List<Predicate<Meeting>> predicateHasPersons = handlePersons(personIndexes);
            List<Predicate<Meeting>> predicateHasNames = handleNames(meetingNames);
            List<Predicate<Meeting>> predicateHasTimes = handleTimes(meetingTimes);
            List<Predicate<Meeting>> predicateHasDescriptions = handleDescriptions(meetingDescriptions);
            List<Predicate<Meeting>> predicateHasPriorities = handlePriorities(meetingPriorities);
            List<Predicate<Meeting>> allPredicates = Stream.of(predicateHasPersons, predicateHasNames,
                    predicateHasTimes,predicateHasDescriptions,predicateHasPriorities)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            Predicate<Meeting> combinedPredicate = allPredicates.stream().reduce(pred -> true,
                    (meetingPredicate, meetingPredicate2) -> meetingPredicate.and(meetingPredicate2));

            return new FindMeetingCommand(combinedPredicate);
        } catch (Exception e) {
            throw new ParseException(SortPersonCommand.MESSAGE_USAGE);
        }

    }

    private Predicate<Meeting> combinePredicateListsToPredicate( List<Predicate<Meeting>> ... predicateLists) {
        List<Predicate<Meeting>> allPredicates = Stream.of(predicateLists)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Predicate<Meeting> combinedPredicate = allPredicates.stream().reduce(pred -> true,
                (meetingPredicate, meetingPredicate2) -> meetingPredicate.and(meetingPredicate2));

        return combinedPredicate;
    }





    private List<Predicate<Meeting>> handlePersons(List<String> personIndexes) throws ParseException {
        ParserUtil.parsePersonsConnection(personIndexes);
        return new ArrayList<Predicate<Meeting>>();
    }

    private List<Predicate<Meeting>> handleNames(List<String> names) throws ParseException {
        return new ArrayList<Predicate<Meeting>>();
    }

    private List<Predicate<Meeting>> handleTimes(List<String> times) throws ParseException {
        return new ArrayList<Predicate<Meeting>>();
    }

    private List<Predicate<Meeting>> handleDescriptions(List<String> descriptions) throws ParseException {
        return new ArrayList<Predicate<Meeting>>();
    }

    private List<Predicate<Meeting>> handlePriorities(List<String> priorities) throws ParseException {
        return new ArrayList<Predicate<Meeting>>();
    }



}
