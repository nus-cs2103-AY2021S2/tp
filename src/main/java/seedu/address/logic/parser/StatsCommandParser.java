package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.statscommands.StatsCommand;
import seedu.address.logic.commands.statscommands.StatsCommandAll;
import seedu.address.logic.commands.statscommands.StatsCommandFaculty;
import seedu.address.logic.commands.statscommands.StatsCommandNus;
import seedu.address.logic.commands.statscommands.StatsCommandResidence;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.SchoolResidence;

public class StatsCommandParser implements Parser<StatsCommand> {
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
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }
}
