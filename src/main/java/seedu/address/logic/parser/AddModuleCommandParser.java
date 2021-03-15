package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.*;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class AddModuleCommandParser implements Parser<AddModuleCommand>{

    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER, PREFIX_MODULE_CODE);

        Index planNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_PLAN_NUMBER).get());
        Index semNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_SEM_NUMBER).get());
        String moduleCode = argumentMultimap.getValue(PREFIX_MODULE_CODE).get();

        return new AddModuleCommand(planNumber, semNumber, moduleCode);
    }
}
