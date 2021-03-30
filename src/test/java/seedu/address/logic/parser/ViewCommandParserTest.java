package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.DetailsPanelTab;
import seedu.address.logic.commands.ViewCommand;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "upcoming events", new ViewCommand(DetailsPanelTab.UPCOMING_EVENTS));

        assertParseSuccess(parser, "streaks", new ViewCommand(DetailsPanelTab.STREAKS));
    }

    @Test
    public void parse_invalidArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);

        assertParseFailure(parser, "a", expectedMessage);

        assertParseFailure(parser, "1", expectedMessage);
    }
}
