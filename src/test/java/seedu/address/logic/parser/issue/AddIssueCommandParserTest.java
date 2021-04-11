package seedu.address.logic.parser.issue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.CATEGORY_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.CATEGORY_DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESCRIPTION_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESCRIPTION_DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_TIMESTAMP_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.ROOM_NUMBER_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.ROOM_NUMBER_DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.STATUS_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.STATUS_DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.TIMESTAMP_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.TIMESTAMP_DESC_11_110;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.issue.TypicalIssues.ISSUE_10_100;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.testutil.issue.IssueBuilder;

public class AddIssueCommandParserTest {
    private AddIssueCommandParser parser = new AddIssueCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Issue expectedIssue = new IssueBuilder(ISSUE_10_100).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                new AddIssueCommand(expectedIssue));

        // multiple room number - last room number accepted
        assertParseSuccess(parser, ROOM_NUMBER_DESC_11_110
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                new AddIssueCommand(expectedIssue));

        // multiple description - last description accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_11_110
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                new AddIssueCommand(expectedIssue));

        // multiple timestamp - last timestamp accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_11_110
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                new AddIssueCommand(expectedIssue));

        // multiple status - last status accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_11_110
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                new AddIssueCommand(expectedIssue));

        // multiple categories - last category accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_11_110
                + CATEGORY_DESC_10_100,
                new AddIssueCommand(expectedIssue));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser,
                DESCRIPTION_DESC_10_100,
                expectedMessage);

        // missing room number prefix
        assertParseFailure(parser,
                ROOM_NUMBER_DESC_10_100,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid room number
        assertParseFailure(parser, INVALID_ROOM_NUMBER_DESC
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                RoomNumber.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, ROOM_NUMBER_DESC_10_100
                + INVALID_DESCRIPTION_DESC
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                Description.MESSAGE_CONSTRAINTS);

        // invalid timestamp
        assertParseFailure(parser, ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + INVALID_TIMESTAMP_DESC
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                Timestamp.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + INVALID_STATUS_DESC
                + CATEGORY_DESC_10_100,
                Status.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + INVALID_CATEGORY_DESC,
                Category.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ROOM_NUMBER_DESC
                + INVALID_DESCRIPTION_DESC
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                RoomNumber.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE));
    }
}
