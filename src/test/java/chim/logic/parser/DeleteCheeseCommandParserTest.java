package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_CHEESE;

import org.junit.jupiter.api.Test;

import chim.logic.commands.DeleteCheeseCommand;

public class DeleteCheeseCommandParserTest {
    private final DeleteCheeseCommandParser parser = new DeleteCheeseCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCheeseCommand() {
        assertParseSuccess(parser, "1", new DeleteCheeseCommand(INDEX_FIRST_CHEESE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCheeseCommand.MESSAGE_USAGE));
    }
}
