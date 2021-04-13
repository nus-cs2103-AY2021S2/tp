package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PolicyCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the PolicyCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the PolicyCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class PolicyCommandParserTest {

    private PolicyCommandParser parser = new PolicyCommandParser();

    @Test
    public void parse_validArgs_returnsPolicyCommand() {
        assertParseSuccess(parser, "1", new PolicyCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INDEX_IS_WORD + "\n"
                + PolicyCommand.MESSAGE_USAGE);
    }
}
