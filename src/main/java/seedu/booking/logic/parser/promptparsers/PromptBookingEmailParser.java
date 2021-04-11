package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;

import java.util.stream.Stream;

import seedu.booking.logic.commands.PromptAddBookingCommand;
import seedu.booking.logic.parser.ArgumentMultimap;
import seedu.booking.logic.parser.ArgumentTokenizer;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.Prefix;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Email;

/**
 * Parses input arguments and creates a new PromptAddBookingCommand object
 */
public class PromptBookingPersonEmailParser implements Parser<PromptAddBookingCommand> {

    /**
     * Parses the given {@code String} of arguments for booking in the context of adding a booking
     * and returns an PromptAddBooking object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptAddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PromptAddBookingCommand.MESSAGE_USAGE));
        }

        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());

        return new PromptAddBookingCommand(email);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
