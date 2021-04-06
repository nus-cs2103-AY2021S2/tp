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
import java.util.Optional;
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
import seedu.address.model.meeting.Priority;


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


        Optional<String> meetingName = argMultimap.getValue(PREFIX_NAME);
        Optional<String> meetingDescription = argMultimap.getValue(PREFIX_DESCRIPTION);
        Optional<String> meetingPriority = argMultimap.getValue(PREFIX_PRIORITY);

        try {
            List<Predicate<Meeting>> predicateHasTimes = handleTimes(meetingTimes);
            Set<Index> personsIndexesToSearch = getPersonsSet(personIndexes);

            Predicate<Meeting> predicateHasName = handleName(meetingName);
            Predicate<Meeting> predicateHasDescription = handleDescription(meetingDescription);
            Predicate<Meeting> predicateHasPriority = handlePriority(meetingPriority);

            List<Predicate<Meeting>> nameDescPriorityPred = new ArrayList<>(
                    List.of(predicateHasName, predicateHasDescription, predicateHasPriority));

            Predicate<Meeting> bigPredicate = combinePredicateListsToPredicate(nameDescPriorityPred,
                    predicateHasTimes);

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

    private Predicate<Meeting> handleName(Optional<String> name) throws ParseException {
        if (name.isEmpty()) {
            return meeting -> true;
        }
        MeetingName parsedNames = ParserUtil.parseMeetingName(name.get());
        Predicate<Meeting> namePred = meeting -> meeting.containsName(parsedNames);
        return namePred;
    }

    private Predicate<Meeting> handleDescription(Optional<String> description) throws ParseException {
        if (description.isEmpty()) {
            return meeting -> true;
        }
        Description parsedDesc = ParserUtil.parseMeetingDescription(description.get());
        Predicate<Meeting> pred = meeting -> meeting.containsDescription(parsedDesc);
        return pred;
    }

    private Predicate<Meeting> handlePriority(Optional<String> priority) throws ParseException {
        if (priority.isEmpty()) {
            return meeting -> true;
        }
        Priority parsedPrio = ParserUtil.parseMeetingPriority(priority.get());
        Predicate<Meeting> pred = meeting -> meeting.hasPriority(parsedPrio);
        return pred;
    }



}
