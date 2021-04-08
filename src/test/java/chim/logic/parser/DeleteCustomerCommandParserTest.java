package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static chim.testutil.TypicalCustomers.ALICE;

import org.junit.jupiter.api.Test;

import chim.logic.commands.DeleteCustomerCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCustomerCommandParserTest {

    private final DeleteCustomerCommandParser parser = new DeleteCustomerCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCustomerCommand() {
        assertParseSuccess(parser, " p/94351253",
                new DeleteCustomerCommand(ALICE.getPhone()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCustomerCommand.MESSAGE_USAGE));
    }
}
