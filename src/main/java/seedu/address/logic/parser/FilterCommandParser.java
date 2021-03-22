package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.FacultyContainsKeywords;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.SchoolResidenceContainsKeywords;
import seedu.address.model.person.VaccinationStatus;
import seedu.address.model.person.VaccinationStatusContainsKeywords;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {



    private static final List<String> VACCINATED_STATUS = VaccinationStatus.getVaccinationStatusAbbreviation();
    private static final List<String> FACULTY = Faculty.getFacultyAbbreviation();
    private static final List<String> SCHOOL_RESIDENCE = SchoolResidence.getResidenceAbbreviation();

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        String condition = args.trim();

        if (VACCINATED_STATUS.contains(condition)) {
            return new FilterCommand(new VaccinationStatusContainsKeywords(condition));
        } else if (FACULTY.contains(condition)) {
            return new FilterCommand(new FacultyContainsKeywords(condition));
        } else if (SCHOOL_RESIDENCE.contains(condition)) {
            return new FilterCommand(new SchoolResidenceContainsKeywords(condition));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }
}
