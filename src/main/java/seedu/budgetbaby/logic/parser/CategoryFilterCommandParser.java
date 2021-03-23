package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.util.stream.Stream;

import seedu.budgetbaby.logic.commands.CategoryFilterCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Category;

/**
 * Parses input arguments and creates a new CategoryFilterCommand object
 */
public class CategoryFilterCommandParser implements BudgetBabyCommandParser<CategoryFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CategoryFilterCommand
     * and returns a CategoryFilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CategoryFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CategoryFilterCommand.MESSAGE_USAGE));
        }

        Category category = ParserUtil.parseTag(argMultimap.getValue(PREFIX_CATEGORY).get());

        return new CategoryFilterCommand(category);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
