package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgetbaby.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.budgetbaby.logic.commands.SearchFrCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;

/**
 * Parses input arguments and creates a new CategoryFilterCommand object
 */
public class SearchFrCommandParser implements BudgetBabyCommandParser<SearchFrCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CategoryFilterCommand
     * and returns a CategoryFilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchFrCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SearchFrCommand.MESSAGE_USAGE));
        }

        Description description;
        Amount amount;
        Category category;


        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            description = null;
        }

        if (arePrefixesPresent(argMultimap, PREFIX_AMOUNT)) {
            amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        } else {
            amount = null;
        }

        if (arePrefixesPresent(argMultimap, PREFIX_CATEGORY)) {
            category = ParserUtil.parseTag(argMultimap.getValue(PREFIX_CATEGORY).get());
        } else {
            category = null;
        }

        return new SearchFrCommand(description, amount, category);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
