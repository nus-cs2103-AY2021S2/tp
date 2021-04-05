package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.student.logic.commands.DeleteApptCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.MatriculationNumber;

/**
 * Parses input arguments and creates a new DeleteApptCommand object
 */
public class DeleteApptCommandParser implements Parser<DeleteApptCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApptCommand
     * and returns a DeleteApptCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteApptCommand parse(String args) throws ParseException {
        assert args != "";
        assert args != null;
        try {
            MatriculationNumber matriculationNumber = ParserUtil.parseMatric(args);
            return new DeleteApptCommand(matriculationNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApptCommand.MESSAGE_USAGE), pe);
        }
    }

}
