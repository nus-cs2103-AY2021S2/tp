package seedu.budgetbaby.logic.parser;

import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.budgetbaby.logic.commands.RedoCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RedoCommand object
 */
public class RedoCommandParser implements BudgetBabyCommandParser<RedoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RedoCommand
     * and returns an RedoCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RedoCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!arePrefixesPresent(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
        }

        return new RedoCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
