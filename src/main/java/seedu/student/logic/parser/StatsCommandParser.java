package seedu.student.logic.parser;

import java.util.List;

import seedu.student.logic.commands.statscommands.StatsCommand;
import seedu.student.logic.commands.statscommands.StatsCommandAll;
import seedu.student.logic.commands.statscommands.StatsCommandFaculty;
import seedu.student.logic.commands.statscommands.StatsCommandNus;
import seedu.student.logic.commands.statscommands.StatsCommandResidence;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.SchoolResidence;

public class StatsCommandParser implements Parser<StatsCommand> {
    public static final String MESSAGE_INVALID_STATS_COMMAND_FORMAT = "Invalid stats parameter entered. \n%1$s";

    private List<String> listResidences = SchoolResidence.getResidenceAbbreviation(); // "DOES_NOT_LIVE_ON_CAMPUS"
    private List<String> listFaculties = Faculty.getFacultyAbbreviation();

    @Override
    public StatsCommand parse(String userInput) throws ParseException {
        String trimmedUserInput = userInput.trim();
        if (listResidences.contains(trimmedUserInput)) { // user input a residence
            SchoolResidence schoolResidence = ParserUtil.parseResidence(trimmedUserInput);
            return new StatsCommandResidence(schoolResidence);
        }
        if (listFaculties.contains(trimmedUserInput)) { // user input a faculty
            Faculty faculty = ParserUtil.parseFaculty(trimmedUserInput);
            return new StatsCommandFaculty(faculty);
        }
        if (trimmedUserInput.equals("NUS")) {
            return new StatsCommandNus();
        }
        if (trimmedUserInput.equals("all")) {
            return new StatsCommandAll();
        }
        // invalid input
        throw new ParseException(String.format(MESSAGE_INVALID_STATS_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }
}
