package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.student.logic.commands.DeleteStudentCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.MatriculationNumber;

/**
 * Parses input arguments and creates a new DeleteStudentCommand object
 */
public class DeleteCommandParser implements Parser<DeleteStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentCommand
     * and returns a DeleteStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteStudentCommand parse(String args) throws ParseException {
        assert args != "";
        assert args != null;
        try {
            MatriculationNumber matriculationNumber = ParserUtil.parseMatric(args);
            return new DeleteStudentCommand(matriculationNumber);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE), pe);
        }
    }

}
