package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TO;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.QueryFoodIntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new QueryFoodIntakeCommand object
 */
public class QueryFoodIntakeCommandParser implements Parser<QueryFoodIntakeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the QueryFoodIntakeCommand
     * and returns a QueryFoodIntakeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryFoodIntakeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_DATE_FROM, PREFIX_DATE_TO);

        LocalDate date = null;
        LocalDate dateFrom = null;
        LocalDate dateTo = null;

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            if (!arePrefixesPresent(argMultimap, PREFIX_DATE_FROM, PREFIX_DATE_TO)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        QueryFoodIntakeCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        }
        if (argMultimap.getValue(PREFIX_DATE_FROM).isPresent()) {
            dateFrom = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_FROM).get());
        }
        if (argMultimap.getValue(PREFIX_DATE_TO).isPresent()) {
            dateTo = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_TO).get());
        }
        return new QueryFoodIntakeCommand(date, dateFrom, dateTo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
