package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FacultyContainsKeywords;
import seedu.address.model.person.SchoolResidenceContainsKeywords;
import seedu.address.model.person.VaccinationStatusContainsKeywords;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    private static final List<String> VACCINATED_STATUS = Arrays.asList("vaccinated", "unvaccinated");

    private static final List<String> FACULTY = Arrays.asList("FASS", "BIZ", "COM", "SCALE", "DEN", "SDE", "DNUS",
            "ENG", "ISEP", "LAW", "MED", "MUSIC", "SPH", "SPP", "SCI", "USP", "YNC");;

    private static final List<String> SCHOOL_RESIDENCE = Arrays.asList("PGPH", "PGPR", "KE7H", "SH", "KRH", "TH", "EH",
            "RH", "RVRC", "YNC", "TC", "CAPT", "RC4", "USP", "UTR", "DOES NOT LIVE ON CAMPUS");

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
