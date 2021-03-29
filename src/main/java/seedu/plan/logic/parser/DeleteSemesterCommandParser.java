package seedu.plan.logic.parser;

import static seedu.plan.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import java.util.stream.Stream;

import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.DeleteSemesterCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteSemesterCommand object
 */
public class DeleteSemesterCommandParser implements Parser<DeleteSemesterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSemesterCommand
     * and returns a DeleteSemesterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSemesterCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER);

            if (!arePrefixesPresent(argMultimap, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteSemesterCommand.MESSAGE_USAGE));
            }

            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PLAN_NUMBER).get());
            int semNumber = ParserUtil.parseSemesterNumber(argMultimap.getValue(PREFIX_SEM_NUMBER).get());
            return new DeleteSemesterCommand(index, semNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteSemesterCommand.MESSAGE_USAGE), pe);
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
