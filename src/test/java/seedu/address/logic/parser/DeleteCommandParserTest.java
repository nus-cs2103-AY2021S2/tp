package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(IDENTIFIER_FIRST_EVENT));
    }

    @Test
    public void parse_negativeIdentifier_throwsParseException() {
        assertParseFailure(parser, "-1",
                ParserUtil.MESSAGE_NEGATIVE_OR_ZERO_IDENTIFIER);
    }

    @Test
    public void parse_outOfRangIdentifier_throwsParseException() {
        assertParseFailure(parser, "100000000000000000",
                ParserUtil.MESSAGE_INVALID_IDENTIFIER);
    }

    @Test
    public void parse_invalidTokenArg_throwsParseExceptionWithUsage() {
        assertParseFailure(parser, "1 n\\",
                ParserUtil.MESSAGE_ADDITIONAL_ARTEFACTS + DeleteCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_IDENTIFIER);
    }
}
