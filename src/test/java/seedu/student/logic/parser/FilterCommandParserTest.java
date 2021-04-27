package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.student.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.student.logic.commands.FilterCommand;
import seedu.student.model.student.FacultyContainsKeywords;
import seedu.student.model.student.SchoolResidenceContainsKeywords;
import seedu.student.model.student.VaccinationStatusContainsKeywords;

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
                new FilterCommand(new VaccinationStatusContainsKeywords("unvaccinated"), "unvaccinated");
        assertParseSuccess(parser, "unvaccinated", expectedFilterCommandByVaccinationStatus);

        FilterCommand secondExpectedVaccinatedFilterCommandByVaccinationStatus =
                new FilterCommand(new VaccinationStatusContainsKeywords("vaccinated"), "vaccinated");
        assertParseSuccess(parser, "vaccinated", secondExpectedVaccinatedFilterCommandByVaccinationStatus);

        FilterCommand thirdExpectedVaccinatedFilterCommandByVaccinationStatus =
                new FilterCommand(new VaccinationStatusContainsKeywords("VACCINATED"), "VACCINATED");
        assertParseSuccess(parser, "VACCINATED", thirdExpectedVaccinatedFilterCommandByVaccinationStatus);

        FilterCommand fourthExpectedVaccinatedFilterCommandByVaccinationStatus =
                new FilterCommand(new VaccinationStatusContainsKeywords("vaccinateD"), "vaccinateD");
        assertParseSuccess(parser, "vaccinateD", fourthExpectedVaccinatedFilterCommandByVaccinationStatus);


        FilterCommand fiveExpectedVaccinatedFilterCommandByVaccinationStatus =
                new FilterCommand(new VaccinationStatusContainsKeywords("Vaccinated"), "Vaccinated");
        assertParseSuccess(parser, "Vaccinated", fiveExpectedVaccinatedFilterCommandByVaccinationStatus);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n unvaccinated \n \t", expectedFilterCommandByVaccinationStatus);
    }

    @Test
    public void parse_validArgs_returnsFilterCommandByFaculty() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommandByFaculty =
                new FilterCommand(new FacultyContainsKeywords("MED"), "MED");
        assertParseSuccess(parser, "MED", expectedFilterCommandByFaculty);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n MED \n \t", expectedFilterCommandByFaculty);
    }

    @Test
    public void parse_validArgs_returnsFilterCommandBySchoolResidence() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommandBySchoolResidence =
                new FilterCommand(new SchoolResidenceContainsKeywords("CAPT"), "CAPT");
        assertParseSuccess(parser, "CAPT", expectedFilterCommandBySchoolResidence);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CAPT \n \t", expectedFilterCommandBySchoolResidence);
    }

    @Test
    public void parse_invalidArgs_failure() {

        // Filter by vaccination status

        assertParseFailure(parser, "NOT_VACCINATED", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));


        assertParseFailure(parser, "not_vaccinated", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "not-vaccinated", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "Not_Vaccinated", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
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
