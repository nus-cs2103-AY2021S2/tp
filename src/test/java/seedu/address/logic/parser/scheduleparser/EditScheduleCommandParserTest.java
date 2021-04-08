package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedulecommands.EditScheduleCommand;

public class EditScheduleCommandParserTest {

    private EditScheduleCommandParser parser =
            new EditScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "2 Chloelim", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditScheduleCommand.MESSAGE_USAGE));
    }
}
