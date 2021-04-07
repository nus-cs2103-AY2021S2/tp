package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deletecommand.DeleteAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Title;

/**
 *  Parses input arguments and creates new DeleteAssignmentCommand object
 */
public class DeleteAssignmentCommandParser extends DeleteCommandParser implements Parser<DeleteAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAssignmentCommand
     * and returns an DeleteAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM, PREFIX_ASSIGNMENT);

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE)
                .filter(Title::isValidTitle)
                .orElseThrow(() -> new ParseException(
                        String.format(Title.MESSAGE_CONSTRAINTS, "Modules"))));

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ASSIGNMENT).get());

        return new DeleteAssignmentCommand(title, index);
    }
}
