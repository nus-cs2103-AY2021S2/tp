package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PAST_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDateTimePastPredicate;
import seedu.address.model.event.Time;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STARTDATE,
                        PREFIX_STARTTIME, PREFIX_ENDDATE, PREFIX_ENDTIME, PREFIX_CATEGORY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STARTDATE, PREFIX_STARTTIME, PREFIX_ENDDATE,
                PREFIX_ENDTIME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Name name = SocheduleParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Date startDate = SocheduleParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTDATE).get());
        Time startTime = SocheduleParserUtil.parseTime(argMultimap.getValue(PREFIX_STARTTIME).get());
        Date endDate = SocheduleParserUtil.parseDate(argMultimap.getValue(PREFIX_ENDDATE).get());
        Time endTime = SocheduleParserUtil.parseTime(argMultimap.getValue(PREFIX_ENDTIME).get());
        Set<Category> categoryList = SocheduleParserUtil.parseCategories(argMultimap.getAllValues(PREFIX_CATEGORY));
        Set<Tag> tagList = SocheduleParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (!isEndDateTimeValid(endDate, endTime)) {
            throw new ParseException(String.format(MESSAGE_PAST_EVENT_END_DATE_TIME, AddEventCommand.MESSAGE_USAGE));
        }

        Event event = new Event(name, startDate, startTime, endDate, endTime, categoryList, tagList);

        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if endDate and endTime are not past.
     */
    private boolean isEndDateTimeValid(Date endDate, Time endTime) {
        return new EventDateTimePastPredicate().test(endDate, endTime);
    }

}
