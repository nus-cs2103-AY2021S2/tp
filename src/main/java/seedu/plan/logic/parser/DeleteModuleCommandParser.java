package seedu.plan.logic.parser;

import java.util.stream.Stream;

import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.DeleteModuleCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

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
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_PLAN_NUMBER,
                CliSyntax.PREFIX_SEM_NUMBER,
                CliSyntax.PREFIX_MODULE_CODE);
        if (!arePrefixesPresent(argumentMultimap, CliSyntax.PREFIX_PLAN_NUMBER,
                CliSyntax.PREFIX_SEM_NUMBER, CliSyntax.PREFIX_MODULE_CODE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteModuleCommand.MESSAGE_USAGE));
        }
        Index planIndex = ParserUtil.parseIndex(argumentMultimap.getValue(CliSyntax.PREFIX_PLAN_NUMBER).get());
        Index semIndex = ParserUtil.parseIndex(argumentMultimap.getValue(CliSyntax.PREFIX_SEM_NUMBER).get());
        String moduleCode = argumentMultimap.getValue(CliSyntax.PREFIX_MODULE_CODE).get();
        String upperCaseModuleCode = moduleCode.toUpperCase();
        return new DeleteModuleCommand(planIndex, semIndex, upperCaseModuleCode);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
