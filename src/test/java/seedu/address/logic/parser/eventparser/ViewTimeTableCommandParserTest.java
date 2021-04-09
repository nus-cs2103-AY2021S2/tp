package seedu.address.logic.parser.eventparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.eventcommands.ViewTimeTableCommand;

public class ViewTimeTableCommandParserTest {

    private ViewTimeTableCommandParser parser = new ViewTimeTableCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "2021-05-24", new ViewTimeTableCommand(LocalDate.of(2021, 5, 24)));
        assertParseSuccess(parser, "", new ViewTimeTableCommand(LocalDate.now()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewTimeTableCommand.MESSAGE_USAGE));
    }
}
