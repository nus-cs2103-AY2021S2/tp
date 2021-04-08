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
import static seedu.iscam.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_DAN;
import static seedu.iscam.logic.commands.CommandTestUtil.STATUS_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.TAG_DESC_PREMIUM;
import static seedu.iscam.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DATETIME_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_LOCATION_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_STATUS_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_PREMIUM;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.iscam.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.iscam.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.iscam.testutil.TypicalMeetings.CLEO;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.AddMeetingCommand;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.testutil.MeetingBuilder;

class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder(CLEO).withTags(VALID_TAG_PREMIUM)
                .withStatus("incomplete").build();

        // TODO: Check if users are supposed to be able to set completion status with "/s"
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CLIENT_NAME_DESC_CLEO
                + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO
                + TAG_DESC_PREMIUM + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeeting));

        // multiple client names - last name accepted
        assertParseSuccess(parser, CLIENT_NAME_DESC_DAN + CLIENT_NAME_DESC_CLEO
                + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO
                + TAG_DESC_PREMIUM + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeeting));

        // multiple dateTimes - last dateTime accepted
        assertParseSuccess(parser, CLIENT_NAME_DESC_CLEO + DATETIME_DESC_DAN
                + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO
                + TAG_DESC_PREMIUM + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeeting));

        // multiple locations - last location accepted
        assertParseSuccess(parser, CLIENT_NAME_DESC_CLEO
                + DATETIME_DESC_CLEO + LOCATION_DESC_DAN + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO
                + TAG_DESC_PREMIUM + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeeting));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, CLIENT_NAME_DESC_CLEO
                + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO + DESCRIPTION_DESC_DAN + DESCRIPTION_DESC_CLEO
                + TAG_DESC_PREMIUM + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeeting));

        // multiple tags - all accepted
        Meeting expectedMeetingMultipleTags = new MeetingBuilder(CLEO).withTags(VALID_TAG_PREMIUM, VALID_TAG_URGENT)
                .withStatus("incomplete").build();
        assertParseSuccess(parser, CLIENT_NAME_DESC_CLEO
                + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO + TAG_DESC_URGENT
                + TAG_DESC_PREMIUM + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeetingMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Meeting expectedMeeting = new MeetingBuilder(CLEO).withTags().withStatus("incomplete").build();
        assertParseSuccess(parser, CLIENT_NAME_DESC_CLEO
                + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO
                + STATUS_DESC_CLEO, new AddMeetingCommand(expectedMeeting));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_CLIENT_NAME_CLEO + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, expectedMessage);

        // missing dateTime prefix
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + VALID_DATETIME_CLEO + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + DATETIME_DESC_CLEO + VALID_LOCATION_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO
                + VALID_DESCRIPTION_CLEO + STATUS_DESC_CLEO, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CLIENT_NAME_CLEO + VALID_DATETIME_CLEO + VALID_LOCATION_CLEO
                + VALID_DESCRIPTION_CLEO + VALID_STATUS_CLEO, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_CLIENT_NAME_DESC + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, Name.MESSAGE_TYPE_CONSTRAINTS);

        // invalid dateTime
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + INVALID_DATETIME_DESC + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, DateTime.MESSAGE_INVALID_FORMAT);

        // invalid dateTime, in the past
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + INVALID_DATETIME_PAST_DESC + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, DateTime.MESSAGE_IN_PAST);

        // invalid description
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO
                + INVALID_DESCRIPTION_DESC + STATUS_DESC_CLEO, Description.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CLIENT_NAME_DESC_CLEO + DATETIME_DESC_CLEO + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + INVALID_TAG_DESC + STATUS_DESC_CLEO, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_CLIENT_NAME_DESC + INVALID_DATETIME_PAST_DESC + LOCATION_DESC_CLEO
                + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO, Name.MESSAGE_TYPE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CLIENT_NAME_DESC_CLEO + DATETIME_DESC_CLEO
                + LOCATION_DESC_CLEO + DESCRIPTION_DESC_CLEO + STATUS_DESC_CLEO,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
    }
}