package seedu.address.logic.parser.Doctor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.doctor.FindDoctorCommand;
import seedu.address.logic.parser.doctor.FindDoctorCommandParser;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindDoctorCommandParserTest {

    private FindDoctorCommandParser parser = new FindDoctorCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindDoctorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {
        // no leading and trailing whitespaces
        FindDoctorCommand expectedFindCommand =
                new FindDoctorCommand(new NameContainsKeywordsPredicate(Arrays.asList("Dr_Hong", "Dr_Ming")));
        assertParseSuccess(parser, "Dr_Hong Dr_Ming", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Dr_Hong \n \t Dr_Ming  \t", expectedFindCommand);
    }

}
