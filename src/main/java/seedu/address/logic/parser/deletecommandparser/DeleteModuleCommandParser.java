package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.deletecommand.DeleteModuleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Title;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteModuleCommandParser extends DeleteCommandParser implements Parser<DeleteModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteModuleCommand
     * and returns a DeleteModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM, PREFIX_ASSIGNMENT);

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE)
                .filter(Title::isValidTitle)
                .orElseThrow(() -> new ParseException(
                        String.format(Title.MESSAGE_CONSTRAINTS, "Modules")
                )));

        return new DeleteModuleCommand(title);
    }

}
