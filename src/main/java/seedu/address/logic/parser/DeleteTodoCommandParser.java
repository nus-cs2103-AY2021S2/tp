package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTodoCommand object
 */
public class DeleteTodoCommandParser implements Parser<DeleteTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTodoCommand
     * and returns a DeleteTodoCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteTodoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTodoCommand.MESSAGE_USAGE));
        }

        Index projectIndex;

        try {
            projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, pe);
        }

        try {
            Index targetTodoIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            return new DeleteTodoCommand(projectIndex, targetTodoIndex);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_TODO_DISPLAYED_INDEX, pe);
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
