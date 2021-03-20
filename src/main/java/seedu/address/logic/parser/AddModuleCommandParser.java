package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses a {add module command}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER, PREFIX_MODULE_CODE);

        Index planNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_PLAN_NUMBER).get());
        Index semNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_SEM_NUMBER).get());
        String moduleCode = argumentMultimap.getValue(PREFIX_MODULE_CODE).get();
        String upperCaseModuleCode = moduleCode.toUpperCase();

        return new AddModuleCommand(planNumber, semNumber, upperCaseModuleCode);
    }
}
