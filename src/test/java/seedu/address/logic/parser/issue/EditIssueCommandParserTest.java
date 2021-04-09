package seedu.address.logic.parser.issue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.CATEGORY_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESCRIPTION_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.DESCRIPTION_DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.INVALID_TIMESTAMP_DESC;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.ROOM_NUMBER_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.STATUS_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.TIMESTAMP_DESC_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.TIMESTAMP_DESC_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_CATEGORY_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_DESCRIPTION_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_DESCRIPTION_11_110;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_ROOM_NUMBER_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_STATUS_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_TIMESTAMP_10_100;
import static seedu.address.logic.commands.issue.IssueCommandTestUtil.VALID_ISSUE_TIMESTAMP_11_110;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX_RANGE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand.EditIssueDescriptor;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.testutil.issue.EditIssueDescriptorBuilder;

public class EditIssueCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditIssueCommand.MESSAGE_USAGE);

    private EditIssueCommandParser parser = new EditIssueCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, ROOM_NUMBER_DESC_10_100, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditIssueCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ROOM_NUMBER_DESC_10_100, MESSAGE_INVALID_INDEX_RANGE);

        // zero index
        assertParseFailure(parser, "0" + ROOM_NUMBER_DESC_10_100, MESSAGE_INVALID_INDEX_RANGE);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ROOM_NUMBER_DESC,
                RoomNumber.MESSAGE_CONSTRAINTS); // invalid room number
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + INVALID_TIMESTAMP_DESC, Timestamp.MESSAGE_CONSTRAINTS); // invalid timestamp
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS); // invalid category

        // invalid description followed by valid status
        assertParseFailure(parser,
                "1" + INVALID_DESCRIPTION_DESC + STATUS_DESC_10_100,
                Description.MESSAGE_CONSTRAINTS);

        // valid status followed by invalid status. The test case for invalid status followed by valid status
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + STATUS_DESC_10_100 + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TIMESTAMP_DESC + INVALID_STATUS_DESC + INVALID_CATEGORY_DESC,
                Timestamp.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased()
                + ROOM_NUMBER_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + STATUS_DESC_10_100
                + CATEGORY_DESC_10_100;

        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_10_100)
                .withDescription(VALID_ISSUE_DESCRIPTION_10_100)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_10_100)
                .withStatus(VALID_ISSUE_STATUS_10_100)
                .withCategory(VALID_ISSUE_CATEGORY_10_100)
                .build();
        EditIssueCommand expectedCommand = new EditIssueCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased()
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100;

        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withDescription(VALID_ISSUE_DESCRIPTION_10_100)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_10_100)
                .build();
        EditIssueCommand expectedCommand = new EditIssueCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // room number
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + ROOM_NUMBER_DESC_10_100;
        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withRoomNumber(VALID_ISSUE_ROOM_NUMBER_10_100)
                .build();
        EditIssueCommand expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_10_100;
        descriptor = new EditIssueDescriptorBuilder()
                .withDescription(VALID_ISSUE_DESCRIPTION_10_100)
                .build();
        expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // timestamp
        userInput = targetIndex.getOneBased() + TIMESTAMP_DESC_10_100;
        descriptor = new EditIssueDescriptorBuilder()
                .withTimestamp(VALID_ISSUE_TIMESTAMP_10_100)
                .build();
        expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_10_100;
        descriptor = new EditIssueDescriptorBuilder()
                .withStatus(VALID_ISSUE_STATUS_10_100)
                .build();
        expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + CATEGORY_DESC_10_100;
        descriptor = new EditIssueDescriptorBuilder()
                .withCategory(VALID_ISSUE_CATEGORY_10_100)
                .build();
        expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased()
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + DESCRIPTION_DESC_10_100
                + TIMESTAMP_DESC_10_100
                + DESCRIPTION_DESC_11_110
                + TIMESTAMP_DESC_11_110;

        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withDescription(VALID_ISSUE_DESCRIPTION_11_110)
                .withTimestamp(VALID_ISSUE_TIMESTAMP_11_110)
                .build();
        EditIssueCommand expectedCommand = new EditIssueCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased()
                + INVALID_STATUS_DESC
                + STATUS_DESC_10_100;
        EditIssueDescriptor descriptor = new EditIssueDescriptorBuilder()
                .withStatus(VALID_ISSUE_STATUS_10_100)
                .build();
        EditIssueCommand expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + DESCRIPTION_DESC_10_100
                + INVALID_CATEGORY_DESC
                + CATEGORY_DESC_10_100;
        descriptor = new EditIssueDescriptorBuilder()
                .withDescription(VALID_ISSUE_DESCRIPTION_10_100)
                .withCategory(VALID_ISSUE_CATEGORY_10_100)
                .build();
        expectedCommand = new EditIssueCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
