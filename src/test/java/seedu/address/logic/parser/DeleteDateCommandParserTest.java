package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IMPORTANT_DATE;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteDateCommand;


public class DeleteDateCommandParserTest {

    private DeleteDateCommandParser parser = new DeleteDateCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteDateCommand() {
        assertParseSuccess(parser, "1", new DeleteDateCommand(INDEX_FIRST_IMPORTANT_DATE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDateCommand.MESSAGE_USAGE));
    }
}
