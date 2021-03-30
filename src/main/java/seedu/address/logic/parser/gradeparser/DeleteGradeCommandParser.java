package seedu.address.logic.parser.gradeparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradecommands.DeleteGradeCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteGradeCommand object
 */
public class DeleteGradeCommandParser implements Parser<DeleteGradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGradeCommand
     * and returns a DeleteGradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGradeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteGradeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE), pe);
        }
    }
}
