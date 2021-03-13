package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TO;

import java.time.LocalDate;

import seedu.address.logic.commands.QueryFoodIntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class QueryFoodIntakeCommandParser implements Parser<QueryFoodIntakeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the Update Command
     * and returns an UpdateFoodItemCommand object for execution.
     *
     * @param args arguments passed in
     * @return an UpdateFoodItemCommand instance
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryFoodIntakeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_DATE_FROM, PREFIX_DATE_TO);

        LocalDate date = null;
        LocalDate dateFrom = null;
        LocalDate dateTo = null;
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
}
