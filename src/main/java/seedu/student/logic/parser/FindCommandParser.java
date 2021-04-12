package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.student.logic.commands.FindCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.appointment.AppointmentContainsMatriculationNumberPredicate;
import seedu.student.model.appointment.AppointmentListContainsMatriculationNumberPredicate;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.StudentContainsMatriculationNumberPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        assert args != "";
        assert args != null;
        try {
            MatriculationNumber matriculationNumber = ParserUtil.parseMatric(args.trim());
            assert matriculationNumber.toString().equals(matriculationNumber.toString().toUpperCase());

            return new FindCommand(new StudentContainsMatriculationNumberPredicate(matriculationNumber),
                    new AppointmentListContainsMatriculationNumberPredicate(matriculationNumber),
                    new AppointmentContainsMatriculationNumberPredicate(matriculationNumber));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE), pe);
        }
    }
}
