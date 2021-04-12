package seedu.address.logic.parser.meetings;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetings.SetTimetableCommand;
import seedu.address.logic.parser.exceptions.ParseException;



public class SetTimetableCommandParserTest {
    private SetTimetableCommandParser timetableCommandParser = new SetTimetableCommandParser();

    @Test
    public void parseValidArguments_parseCommandSuccess() throws ParseException {
        assertParseSuccess(timetableCommandParser, "2021-03-03",
                new SetTimetableCommand(LocalDate.of(2021, 3, 3)));
    }

    @Test
    public void parseValidArguments_noArguments() throws ParseException {
        try {
            timetableCommandParser.parse("");
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseInvalidArgs_throwsParseException() throws ParseException {
        assertParseFailure(timetableCommandParser, "202102",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTimetableCommand.MESSAGE_USAGE));
    }


}
