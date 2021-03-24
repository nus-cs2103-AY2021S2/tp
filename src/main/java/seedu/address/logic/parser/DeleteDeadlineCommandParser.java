package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_TASK_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDeadlineCommand object
 */
public class DeleteDeadlineCommandParser implements Parser<DeleteDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDeadlineCommand
     * and returns a DeleteDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteDeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REMOVE_TASK_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_REMOVE_TASK_INDEX) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDeadlineCommand.MESSAGE_USAGE));
        }

        Index projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        Index targetDeadlineIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_REMOVE_TASK_INDEX).get());
        return new DeleteDeadlineCommand(projectIndex, targetDeadlineIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
