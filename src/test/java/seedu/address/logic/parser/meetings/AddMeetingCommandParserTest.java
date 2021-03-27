
package seedu.address.logic.parser.meetings;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.DESCRIPTION_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.DESCRIPTION_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.END_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.END_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.NAME_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.NAME_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.PRIORITY_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.PRIORITY_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.START_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.START_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.TAG_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.TAG_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_NAME_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_START_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_TAG_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_TAG_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_TERMINATE_MEETING1;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.GROUP_DESC_FRIEND;
import static seedu.address.logic.commands.persons.PersonCommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetings.AddMeetingCommand;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.testutil.MeetingBuilder;

class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder(MEETING1).withGroups(VALID_TAG_MEETING1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MEETING1
                + START_DESC_MEETING1 + END_DESC_MEETING1
                + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_MEETING2 + NAME_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1 + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));

        // multiple start time - last start time accepted
        assertParseSuccess(parser, NAME_DESC_MEETING1 + START_DESC_MEETING2 + START_DESC_MEETING1
                + END_DESC_MEETING1 + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));

        // multiple end time - last end time accepted
        assertParseSuccess(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING2
                + END_DESC_MEETING1 + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));

        // multiple priorities - last priority accepted
        assertParseSuccess(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1
                + PRIORITY_DESC_MEETING2 + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1
                + END_DESC_MEETING1 + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING2
                + DESCRIPTION_DESC_MEETING1 + TAG_DESC_MEETING1, new AddMeetingCommand(expectedMeeting));

        // multiple groups - all accepted
        Meeting expectedMeetingMultipleGroups = new MeetingBuilder(MEETING1).withGroups(VALID_TAG_MEETING1,
                VALID_TAG_MEETING2)
                .build();
        assertParseSuccess(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1
                + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1 + TAG_DESC_MEETING2, new AddMeetingCommand(expectedMeetingMultipleGroups));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero group
        Meeting expectedMeeting = new MeetingBuilder(MEETING1).withPriority("1").withDescription("")
                .withGroups().build();
        assertParseSuccess(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1,
                new AddMeetingCommand(expectedMeeting));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1,
                expectedMessage);

        // missing start prefix
        assertParseFailure(parser, NAME_DESC_MEETING1 + VALID_START_MEETING1 + END_DESC_MEETING1,
                expectedMessage);

        // missing end prefix
        assertParseFailure(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + VALID_TERMINATE_MEETING1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MEETING1 + VALID_START_MEETING1 + VALID_TERMINATE_MEETING1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + START_DESC_MEETING1 + END_DESC_MEETING1
                + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, MeetingName.MESSAGE_CONSTRAINTS);

        // invalid date time
        assertParseFailure(parser, NAME_DESC_MEETING1 + INVALID_DATETIME_DESC + END_DESC_MEETING1
                + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, DateTime.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1
                + INVALID_PRIORITY_DESC + DESCRIPTION_DESC_MEETING1
                + TAG_DESC_MEETING1, Priority.MESSAGE_CONSTRAINTS);


        // invalid group
        assertParseFailure(parser, NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1
                + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                + INVALID_GROUP_DESC, Group.MESSAGE_CONSTRAINTS);


        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MEETING1 + START_DESC_MEETING1
                        + END_DESC_MEETING1 + PRIORITY_DESC_MEETING1 + DESCRIPTION_DESC_MEETING1
                        + GROUP_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
    }
}
