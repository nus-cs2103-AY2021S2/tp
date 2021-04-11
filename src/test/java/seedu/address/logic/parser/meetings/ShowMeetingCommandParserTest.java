package seedu.address.logic.parser.meetings;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetings.ShowMeetingCommand;

public class ShowMeetingCommandParserTest {

    private ShowMeetingCommandParser parser = new ShowMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for specified meeting index
        ShowMeetingCommand expectedShowMeetingCommand =
                new ShowMeetingCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedShowMeetingCommand);

        // multiple whitespaces before and after specified meeting index
        assertParseSuccess(parser, "    1   ", expectedShowMeetingCommand);
    }
}
