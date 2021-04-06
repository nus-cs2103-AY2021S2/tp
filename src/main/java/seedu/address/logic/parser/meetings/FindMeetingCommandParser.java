package seedu.address.logic.parser.meetings;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.meetings.FindMeetingCommand;
import seedu.address.logic.commands.persons.SortPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.PersonSortDirection;
import seedu.address.model.person.PersonSortOption;


public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    /**
     * Parses {@code args} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public FindMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_BY, PREFIX_SORT_DIRECTION);


        List<Predicate<Meeting>> allPredicates = new ArrayList<Predicate<Meeting>>();;

        Predicate<Meeting> combinedPredicate = allPredicates.stream().reduce(pred -> true,
                (meetingPredicate, meetingPredicate2) -> meetingPredicate.and(meetingPredicate2));




        try {
            PersonSortOption option = PersonSortOption.valueOf(argMultimap.getValue(PREFIX_SORT_BY).get());
            PersonSortDirection direction = PersonSortDirection.valueOf(argMultimap.getValue(PREFIX_SORT_DIRECTION).
                    get());
            return new FindMeetingCommand(combinedPredicate);
        } catch (Exception e) {
            throw new ParseException(SortPersonCommand.MESSAGE_USAGE);
        }

    }



}
