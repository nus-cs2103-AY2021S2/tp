package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

import seedu.budgetbaby.logic.commands.AddFrCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddFrCommandParser implements BudgetBabyCommandParser<AddFrCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFrCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_CATEGORY, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_AMOUNT)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFrCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());

        Date timestamp = new Date();
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            timestamp = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TIME).get());
        }

        Set<Category> categoryList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_CATEGORY));

        FinancialRecord record = new FinancialRecord(description, amount, timestamp, categoryList);

        return new AddFrCommand(record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
