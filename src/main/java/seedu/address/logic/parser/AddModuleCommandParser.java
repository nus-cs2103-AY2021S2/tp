package seedu.address.logic.parser;

import seedu.address.logic.commands.addcommand.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

import static seedu.address.logic.parser.CliSyntax.*;

public class AddModuleCommandParser extends AddCommandParser implements Parser<AddModuleCommand> {
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM, PREFIX_ASSIGNMENT);

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE).get());

        Module module = new Module(title);

        return new AddModuleCommand(module);
    }
}
