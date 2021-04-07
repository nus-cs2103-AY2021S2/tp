package seedu.plan.logic.parser;

import static seedu.plan.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.AddModuleCommand;
import seedu.plan.logic.commands.AddSemesterCommand;
import seedu.plan.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses a {add module command}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER,
                        PREFIX_MODULE_CODE, PREFIX_GRADE);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER, PREFIX_MODULE_CODE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }

        Index planNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_PLAN_NUMBER).get());
        Index semNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_SEM_NUMBER).get());
        String moduleCode = argumentMultimap.getValue(PREFIX_MODULE_CODE).get();
        String upperCaseModuleCode = moduleCode.toUpperCase();

        //System.out.println(argumentMultimap.getValue(PREFIX_MODULE_CODE).get());
        //System.out.println(argumentMultimap.getValue(PREFIX_GRADE).get());

        if (argumentMultimap.getValue(PREFIX_GRADE).isPresent()) {
            String grade = argumentMultimap.getValue(PREFIX_GRADE).get();
            return new AddModuleCommand(planNumber, semNumber, upperCaseModuleCode, grade);
        } else {
            return new AddModuleCommand(planNumber, semNumber, upperCaseModuleCode);
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
