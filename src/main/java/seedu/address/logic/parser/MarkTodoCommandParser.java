package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK_TASK_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkTodoCommand object
 */
public class MarkTodoCommandParser implements Parser<MarkTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTodoCommand
     * and returns a MarkTodoCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTodoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MARK_TASK_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_MARK_TASK_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTodoCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            Index targetTodoIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MARK_TASK_INDEX).get());
            return new MarkTodoCommand(index, targetTodoIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTodoCommand.MESSAGE_USAGE),
                    pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
