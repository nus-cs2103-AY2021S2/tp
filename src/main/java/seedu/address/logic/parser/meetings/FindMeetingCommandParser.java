package seedu.address.logic.parser.meetings;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CONNECTION;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetings.FindMeetingCommand;
import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;


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
        List<String> meetingTimes = argMultimap.getAllValues(PREFIX_TIME);


        List<String> meetingNames = argMultimap.getAllValues(PREFIX_NAME);
        List<String> meetingDescriptions = argMultimap.getAllValues(PREFIX_DESCRIPTION);
        List<String> meetingPriorities = argMultimap.getAllValues(PREFIX_PRIORITY);

        try {
            List<Predicate<Meeting>> predicateHasNames = handleNames(meetingNames);
            List<Predicate<Meeting>> predicateHasTimes = handleTimes(meetingTimes);
            List<Predicate<Meeting>> predicateHasDescriptions = handleDescriptions(meetingDescriptions);
            List<Predicate<Meeting>> predicateHasPriorities = handlePriorities(meetingPriorities);

            Set<Index> personsIndexesToSearch = getPersonsSet(personIndexes);

            Predicate<Meeting> bigPredicate = combinePredicateListsToPredicate(predicateHasNames,
                    predicateHasTimes,predicateHasDescriptions,predicateHasPriorities);

            return new FindMeetingCommand(bigPredicate, personsIndexesToSearch);
        } catch (Exception e) {
            throw new ParseException(SortPersonCommand.MESSAGE_USAGE);
        }

    }

    @SafeVarargs
    private Predicate<Meeting> combinePredicateListsToPredicate(List<Predicate<Meeting>> ... predicateLists) {
        List<Predicate<Meeting>> allPredicates = Stream.of(predicateLists)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Predicate<Meeting> combinedPredicate = allPredicates.stream().reduce(pred -> true,
                (meetingPredicate, meetingPredicate2) -> meetingPredicate.and(meetingPredicate2));

        return combinedPredicate;
    }

    private List<Predicate<Meeting>> handlePersons(List<String> personIndexes) throws ParseException {
        if (personIndexes.isEmpty()) {
            return new ArrayList<Predicate<Meeting>>();
        }
        Set<Index> personIndexSet = ParserUtil.parsePersonsConnection(personIndexes);
        return new ArrayList<Predicate<Meeting>>();
    }

    private Set<Index> getPersonsSet(List<String> personIndexes) throws ParseException {
        if (personIndexes.isEmpty()) {
            return new HashSet<>();
        }
        Set<Index> personIndexSet = ParserUtil.parsePersonsConnection(personIndexes);
        return personIndexSet;
    }


    private List<Predicate<Meeting>> handleNames(List<String> names) throws ParseException {
        List<Predicate<Meeting>> outArray = new ArrayList<Predicate<Meeting>>();
        if (names.isEmpty()) {
            return outArray;
        }
        Set<MeetingName> parsedNames = ParserUtil.parseMeetingNames(names);
        Predicate<Meeting> namePred = meeting -> parsedNames.stream().allMatch(meetingName ->
                meeting.containsName(meetingName));
        outArray.add(namePred);
        return outArray;
    }

    private List<Predicate<Meeting>> handleTimes(List<String> times) throws ParseException {
        List<Predicate<Meeting>> outArray = new ArrayList<Predicate<Meeting>>();
        if (times.isEmpty()) {
            return outArray;
        }
        Set<DateTime> parsedTimes = ParserUtil.parseMeetingDateTimes(times);
        Predicate<Meeting> timePred = meeting -> parsedTimes.stream().allMatch(time ->
                meeting.hasTime(time));
        return outArray;
    }

    private List<Predicate<Meeting>> handleDescriptions(List<String> descriptions) throws ParseException {
        List<Predicate<Meeting>> outArray = new ArrayList<Predicate<Meeting>>();
        if (descriptions.isEmpty()) {
            return outArray;
        }
        Set<Description> parsedDescriptions = ParserUtil.parseMeetingDescriptions(descriptions);
        Predicate<Meeting> timePred = meeting -> parsedDescriptions.stream().allMatch(desc ->
                meeting.des);


        return outArray;
    }

    private List<Predicate<Meeting>> handlePriorities(List<String> priorities) throws ParseException {
        List<Predicate<Meeting>> outArray = new ArrayList<Predicate<Meeting>>();
        if (priorities.isEmpty()) {
            return outArray;
        }
        return outArray;
    }



}
