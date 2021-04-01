package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;

import java.util.stream.Stream;

import seedu.booking.logic.commands.FindPersonCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.EmailContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand.
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EMAIL);

        String email;
        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL) || argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        email = String.valueOf(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));

        return new FindPersonCommand(new EmailContainsKeywordsPredicate(email));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
