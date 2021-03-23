package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.YearMonth;
import java.util.List;

import seedu.budgetbaby.logic.commands.ViewMonthCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;

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
}
