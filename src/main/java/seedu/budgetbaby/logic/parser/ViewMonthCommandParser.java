package seedu.budgetbaby.logic.parser;

import seedu.budgetbaby.logic.commands.AddFrCommand;
import seedu.budgetbaby.logic.commands.ViewMonthCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

/**
 * Parses input arguments and creates a new ViewMonthCommand object
 */
public class ViewMonthCommandParser implements BudgetBabyCommandParser<ViewMonthCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewMonthCommand
     * and returns an ViewMonthCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMonthCommand parse(String args) throws ParseException {
        List<String> arguments =
            ArgumentTokenizer.tokenizeWithoutPrefix(args);
        if (arguments.size() != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewMonthCommand.MESSAGE_USAGE));
        }
        YearMonth month = ParserUtil.parseYearMonth(arguments.get(0));

        return new ViewMonthCommand(month);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}