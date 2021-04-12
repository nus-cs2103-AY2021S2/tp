package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndices.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteEntryCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteEntryCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteEntryCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteEntryCommandParserTest {

    private final DeleteEntryCommandParser parser = new DeleteEntryCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteEntryCommand() {
        assertParseSuccess(parser, "1", new DeleteEntryCommand(INDEX_FIRST));
    }

    @Test
    public void parser_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEntryCommand.MESSAGE_USAGE));
    }
}
