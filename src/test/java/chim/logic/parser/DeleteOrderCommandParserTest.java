package chim.logic.parser;

import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import chim.commons.core.Messages;
import chim.logic.commands.DeleteOrderCommand;
import chim.testutil.TypicalIndexes;

public class DeleteOrderCommandParserTest {
    private DeleteOrderCommandParser parser = new DeleteOrderCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteOrderCommand() {
        assertParseSuccess(parser, "1", new DeleteOrderCommand(TypicalIndexes.INDEX_FIRST_ORDER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteOrderCommand.MESSAGE_USAGE));
    }
}
