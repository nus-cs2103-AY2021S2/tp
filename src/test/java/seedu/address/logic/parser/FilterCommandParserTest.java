package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.FacultyContainsKeywords;
import seedu.address.model.person.SchoolResidenceContainsKeywords;
import seedu.address.model.person.VaccinationStatusContainsKeywords;

public class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommandByVaccinationStatus() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommandByVaccinationStatus =
                new FilterCommand(new VaccinationStatusContainsKeywords("NOT_VACCINATED"));
        assertParseSuccess(parser, "NOT_VACCINATED", expectedFilterCommandByVaccinationStatus);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n NOT_VACCINATED \n \t", expectedFilterCommandByVaccinationStatus);
    }

    @Test
    public void parse_validArgs_returnsFilterCommandByFaculty() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommandByFaculty =
                new FilterCommand(new FacultyContainsKeywords("MED"));
        assertParseSuccess(parser, "MED", expectedFilterCommandByFaculty);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n MED \n \t", expectedFilterCommandByFaculty);
    }

    @Test
    public void parse_validArgs_returnsFilterCommandBySchoolResidence() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommandBySchoolResidence =
                new FilterCommand(new SchoolResidenceContainsKeywords("CAPT"));
        assertParseSuccess(parser, "CAPT", expectedFilterCommandBySchoolResidence);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CAPT \n \t", expectedFilterCommandBySchoolResidence);
    }

    @Test
    public void parse_invalidArgs_failure() {

        // Filter by vaccination status
        assertParseFailure(parser, "not vaccinated", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "vaccinate", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        // Filter by faculty
        assertParseFailure(parser, "Science", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "Computing", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        // Filter by school residence
        assertParseFailure(parser, "RVRT", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "College of Alice & Peter Tan", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

    }
}
