//@@ ZhangAnli
package dog.pawbook.logic.parser;

import static dog.pawbook.logic.commands.CommandTestUtil.EMPTY_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_DATE_APRIL_31ST;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATE_1ST_JAN;
import static dog.pawbook.logic.commands.CommandTestUtil.WHITESPACE_STRING;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.ScheduleCommand;

public class ScheduleCommandParserTest {

    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_invalidDate_failure() {

        String messageInvalidDayOfTheMonth = "Day of the month does not exist!";

        // invalid date input failure
        assertParseFailure(parser, INVALID_DATE_APRIL_31ST, messageInvalidDayOfTheMonth);

        // invalid date input with whitespace failure
        assertParseFailure(parser, WHITESPACE_STRING + INVALID_DATE_APRIL_31ST + WHITESPACE_STRING,
                messageInvalidDayOfTheMonth);
    }

    @Test
    public void parse_validDate_success() {

        LocalDate sampleDate = LocalDate.of(2021, 1, 1);
        ScheduleCommand expectedCommand = new ScheduleCommand(sampleDate);

        // valid date success
        assertParseSuccess(parser, VALID_DATE_1ST_JAN, expectedCommand);

        // valid date with whitespace string success
        assertParseSuccess(parser, WHITESPACE_STRING + VALID_DATE_1ST_JAN + WHITESPACE_STRING,
                expectedCommand);
    }

    @Test
    public void parse_emptyArg_success() {

        ScheduleCommand expectedCommand = new ScheduleCommand(LocalDate.now());

        // valid date success
        assertParseSuccess(parser, EMPTY_STRING, expectedCommand);

        // valid date with whitespace string success
        assertParseSuccess(parser, WHITESPACE_STRING, expectedCommand);
    }
}
