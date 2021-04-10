package seedu.iscam.logic.parser.meetingcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.commands.CommandTestUtil.CLIENT_NAME_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.CLIENT_NAME_DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.DATETIME_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.DATETIME_DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.DESCRIPTION_DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_CLIENT_NAME_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_DATETIME_PAST_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.STATUS_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.STATUS_DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.TAG_DESC_PREMIUM;
import static seedu.iscam.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DATETIME_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DATETIME_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_STATUS_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_STATUS_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_PREMIUM;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.EditMeetingCommand;
import seedu.iscam.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.CompletionStatus;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.testutil.EditMeetingDescriptorBuilder;

class EditMeetingCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CLIENT_NAME_CLEO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CLIENT_NAME_DESC, Name.MESSAGE_TYPE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC, DateTime.MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + INVALID_DATETIME_PAST_DESC, DateTime.MESSAGE_IN_PAST);
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, CompletionStatus.MESSAGE_CONSTRAINTS);

        // invalid dateTime followed by valid Location
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC + LOCATION_DESC_CLEO, DateTime.MESSAGE_INVALID_FORMAT);

        // valid dateTime followed by invalid dateTime. The test case for invalid dateTime followed by valid dateTime
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DATETIME_DESC_CLEO + INVALID_DATETIME_DESC, DateTime.MESSAGE_INVALID_FORMAT);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Client} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_PREMIUM + TAG_DESC_URGENT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_PREMIUM + TAG_EMPTY + TAG_DESC_URGENT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_PREMIUM + TAG_DESC_URGENT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_CLIENT_NAME_DESC + INVALID_DATETIME_DESC + VALID_LOCATION_CLEO
                + VALID_DESCRIPTION_CLEO, Name.MESSAGE_TYPE_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput = targetIndex.getOneBased() + DATETIME_DESC_DAN + LOCATION_DESC_DAN
                + STATUS_DESC_DAN + TAG_DESC_PREMIUM + CLIENT_NAME_DESC_DAN + TAG_DESC_URGENT
                + DESCRIPTION_DESC_DAN;

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withClientName(VALID_CLIENT_NAME_DAN).withDateTime(VALID_DATETIME_DAN)
                .withLocation(VALID_LOCATION_DAN).withDescription(VALID_DESCRIPTION_DAN)
                .withTags(VALID_TAG_URGENT, VALID_TAG_PREMIUM).withStatus(VALID_STATUS_DAN).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // client name
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + CLIENT_NAME_DESC_CLEO;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withClientName(VALID_CLIENT_NAME_CLEO).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // dateTime
        userInput = targetIndex.getOneBased() + DATETIME_DESC_CLEO;
        descriptor = new EditMeetingDescriptorBuilder()
                .withDateTime(VALID_DATETIME_CLEO).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_CLEO;
        descriptor = new EditMeetingDescriptorBuilder()
                .withLocation(VALID_LOCATION_CLEO).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_CLEO;
        descriptor = new EditMeetingDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_CLEO).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_URGENT;
        descriptor = new EditMeetingDescriptorBuilder()
                .withTags(VALID_TAG_URGENT).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_CLEO;
        descriptor = new EditMeetingDescriptorBuilder()
                .withStatus(VALID_STATUS_CLEO).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + CLIENT_NAME_DESC_CLEO + DATETIME_DESC_CLEO
                + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO + TAG_DESC_URGENT
                + CLIENT_NAME_DESC_DAN + DATETIME_DESC_DAN + LOCATION_DESC_DAN + DESCRIPTION_DESC_DAN
                + STATUS_DESC_DAN + TAG_DESC_PREMIUM;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withClientName(VALID_CLIENT_NAME_DAN)
                .withDateTime(VALID_DATETIME_DAN).withLocation(VALID_LOCATION_DAN)
                .withDescription(VALID_DESCRIPTION_DAN).withTags(VALID_TAG_URGENT, VALID_TAG_PREMIUM)
                .withStatus(VALID_STATUS_DAN).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + INVALID_LOCATION_DESC + LOCATION_DESC_CLEO;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withLocation(VALID_LOCATION_CLEO).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        targetIndex = INDEX_FIRST_ITEM;
        userInput = targetIndex.getOneBased() + DATETIME_DESC_CLEO + INVALID_LOCATION_DESC + LOCATION_DESC_CLEO;
        descriptor = new EditMeetingDescriptorBuilder().withDateTime(VALID_DATETIME_CLEO)
                .withLocation(VALID_LOCATION_CLEO).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTags().build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
