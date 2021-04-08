package seedu.address.logic.parser.scheduleparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulecommands.DeleteScheduleCommand;

public class DeleteScheduleCommandParserTest {

    private DeleteScheduleCommandParser parser = new DeleteScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroArg_throwsParseException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeArg_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteScheduleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_returnsDeleteScheduleCommand() {
        DeleteScheduleCommand deleteScheduleCommand =
                new DeleteScheduleCommand(Index.fromOneBased(1));

        assertParseSuccess(parser, "1", deleteScheduleCommand);
    }
}
