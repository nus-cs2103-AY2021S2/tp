package seedu.address.logic.parser.issue;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX_TYPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.CloseIssueCommand;

/**
 * Similar to DeleteIssueCommandParserTest
 */
public class CloseIssueCommandParserTest {

    private CloseIssueCommandParser parser = new CloseIssueCommandParser();

    @Test
    public void parse_validArgs_returnsCloseCommand() {
        assertParseSuccess(parser, "1", new CloseIssueCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_INDEX_TYPE,
                CloseIssueCommand.MESSAGE_USAGE));
    }
}
