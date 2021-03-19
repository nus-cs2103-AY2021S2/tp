package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteSemesterCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

/**
 * Parse input arguments and return a new DeleteModuleCommand Object
 */
public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {
    /**
     * Parse the given {@code String} of arguments in the context of the DeleteModuleCommand
     * and returns a DeleteModuleCommand object for execution
     * @throws ParseException if the format is wrong
     */
    public DeleteModuleCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argumentMultimap =
                    ArgumentTokenizer.tokenize(args,
                            PREFIX_PLAN_NUMBER,
                            PREFIX_SEM_NUMBER,
                            PREFIX_MODULE_CODE);
            if (!arePrefixesPresent(argumentMultimap, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER, PREFIX_MODULE_CODE)
                || !argumentMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteModuleCommand.MESSAGE_USAGE));
            }
            Index planIndex = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_PLAN_NUMBER).get());
            Index semIndex = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_SEM_NUMBER).get());
            String moduleCode = argumentMultimap.getValue(PREFIX_MODULE_CODE).get();
            return new DeleteModuleCommand(planIndex, semIndex, moduleCode);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSemesterCommand.MESSAGE_USAGE), e);
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
