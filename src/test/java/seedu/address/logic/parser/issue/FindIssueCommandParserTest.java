package seedu.address.logic.parser.issue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.model.issue.RoomNumberOrTagContainsKeywordsPredicate;

public class FindIssueCommandParserTest {

    private FindIssueCommandParser parser = new FindIssueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIssueCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindIssueCommand expectedFindIssueCommand = new FindIssueCommand(
                new RoomNumberOrTagContainsKeywordsPredicate(Arrays.asList("10-", "20-")));
        assertParseSuccess(parser, "10- 20-", expectedFindIssueCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 10- \n \t 20-  \t", expectedFindIssueCommand);
    }

}
