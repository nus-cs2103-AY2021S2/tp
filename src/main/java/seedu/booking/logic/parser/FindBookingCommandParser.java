package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.booking.logic.commands.FindBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Booking;

/**
 * Parses input arguments and creates a new FindBookingCommand object.
 */
public class FindBookingCommandParser implements Parser<FindBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookingCommand
     * and returns a FindBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);

        List<Predicate<Booking>> predicateList = new ArrayList<>();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_DATE, PREFIX_TAG,
                PREFIX_VENUE, PREFIX_DESCRIPTION);

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            predicateList.add(ParserUtil.parseBookingContainsBookerPredicate(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            predicateList.add(ParserUtil.parseBookingWithinDatePredicate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            predicateList.add(ParserUtil.parseBookingContainsTagPredicate(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            predicateList.add(ParserUtil.parseBookingContainsVenuePredicate(argMultimap.getValue(PREFIX_VENUE).get()));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            predicateList.add(ParserUtil.parseBookingContainsDescriptionPredicate(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (predicateList.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
        }

        return new FindBookingCommand(predicateList);
    }

}
