package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.plan.Description;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeletePlanCommandParser implements Parser<DeletePlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePlanCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER);

            if (!arePrefixesPresent(argMultimap, PREFIX_PLAN_NUMBER)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePlanCommand.MESSAGE_USAGE));
            }

            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PLAN_NUMBER).get());
            return new DeletePlanCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePlanCommand.MESSAGE_USAGE), pe);
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
