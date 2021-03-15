package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CHEESE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCheeseCommand;

public class DeleteCheeseCommandParserTest {
    private DeleteCheeseCommandParser parser = new DeleteCheeseCommandParser();

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
