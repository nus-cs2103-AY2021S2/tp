package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.plan.Description;
import seedu.address.model.plan.Module;
import seedu.address.model.plan.exceptions.ModuleExceptions;

import javax.lang.model.element.ModuleElement;

public class AddModuleCommandParser implements Parser<AddModuleCommand>{

    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER, PREFIX_MODULE_CODE);

        Index planNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_PLAN_NUMBER).get());
        Index semNumber = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_SEM_NUMBER).get());
        String moduleCode = ParserUtil.parseModuleCode(argumentMultimap.getValue(PREFIX_MODULE_CODE).get());

        Module module;

        try {
            module = Module.seekModule(moduleCode);
            return new AddModuleCommand(module, planNumber, semNumber);
        } catch (ModuleExceptions moduleExceptions) {
            throw new ParseException("Cannot find module");
        }
    }
}
