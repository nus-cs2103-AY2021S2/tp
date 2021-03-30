package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.budgetbaby.logic.commands.ResetFilterCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetFilterCommand object
 */
public class ResetFilterCommandParser implements BudgetBabyCommandParser<ResetFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ResetFiltercommand
     * and returns an ResetFilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ResetFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!arePrefixesPresent(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetFilterCommand.MESSAGE_USAGE));
        }

        return new ResetFilterCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
