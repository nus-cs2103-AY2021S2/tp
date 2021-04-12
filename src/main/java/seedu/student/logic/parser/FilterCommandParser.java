package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.student.logic.commands.FilterCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.FacultyContainsKeywords;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.SchoolResidenceContainsKeywords;
import seedu.student.model.student.VaccinationStatus;
import seedu.student.model.student.VaccinationStatusContainsKeywords;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        assert args != null;
        String condition = args.trim();

        if (VaccinationStatus.isValidStatus(condition.toUpperCase())) {
            return new FilterCommand(new VaccinationStatusContainsKeywords(condition.toUpperCase()),
                    condition.toLowerCase());
        } else if (Faculty.isValidFaculty(condition)) {
            return new FilterCommand(new FacultyContainsKeywords(condition), condition);
        } else if (SchoolResidence.isValidResidence(condition)) {
            return new FilterCommand(new SchoolResidenceContainsKeywords(condition), condition);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }

}
