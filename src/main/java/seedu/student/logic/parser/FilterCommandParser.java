package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.student.logic.commands.FilterCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.*;

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

        String condition = args.trim();

        if(VaccinationStatus.isExist(condition.toUpperCase())){
            return new FilterCommand(new VaccinationStatusContainsKeywords(condition.toUpperCase()));
        } else if (Faculty.isExist(condition)) {
            return new FilterCommand(new FacultyContainsKeywords(condition));
        } else if (SchoolResidence.isExist(condition)) {
            System.out.println("hi");
            return new FilterCommand(new SchoolResidenceContainsKeywords(condition));
        }  else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }

}
