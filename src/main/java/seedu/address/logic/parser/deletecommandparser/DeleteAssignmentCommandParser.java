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
     * Parses the {@code String} of arguments of a delete assignment command
     * to execute the specific delete command
     * @param args
     * @return DeleteAssignmentCommand
     * @throws ParseException
     */
    @Override
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM, PREFIX_ASSIGNMENT);
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE).get());
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
        return new DeleteAssignmentCommand(title, index);
    }
}
