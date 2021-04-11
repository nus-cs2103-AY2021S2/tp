package seedu.address.logic.parser.meetings;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.END_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.END_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.GROUP_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.GROUP_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.NAME_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.NAME_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.PRIORITY_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.PRIORITY_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.START_DESC_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.START_DESC_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_GROUP_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_GROUP_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_NAME_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_NAME_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_PRIORITY_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_PRIORITY_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_START_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_START_MEETING2;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_TERMINATE_MEETING1;
import static seedu.address.logic.commands.meetings.MeetingCommandTestUtil.VALID_TERMINATE_MEETING2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetings.EditMeetingCommand;
import seedu.address.logic.commands.meetings.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingCommandParserTest {
    private static final String GROUP_EMPTY = " " + PREFIX_GROUP;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_MEETING1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_MEETING1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_MEETING1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, MeetingName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC, DateTime.MESSAGE_CONSTRAINTS); // invalid start time
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC, DateTime.MESSAGE_CONSTRAINTS); // invalid end time
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_GROUP_DESC, Group.MESSAGE_CONSTRAINTS); // invalid group
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + START_DESC_MEETING2 + END_DESC_MEETING2
                + PRIORITY_DESC_MEETING1 + NAME_DESC_MEETING1 + GROUP_DESC_MEETING1 + GROUP_DESC_MEETING2;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withName(VALID_NAME_MEETING1)
                .withStart(VALID_START_MEETING2)
                .withTerminate(VALID_TERMINATE_MEETING2)
                .withPriority(VALID_PRIORITY_MEETING1)
                .withGroups(VALID_GROUP_MEETING1, VALID_GROUP_MEETING2).build();

        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_MEETING2 + NAME_DESC_MEETING1;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withPriority(VALID_PRIORITY_MEETING2)
                .withName(VALID_NAME_MEETING1).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_MEETING1;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_MEETING1).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_MEETING1;
        descriptor = new EditMeetingDescriptorBuilder().withPriority(VALID_PRIORITY_MEETING1).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // groups
        userInput = targetIndex.getOneBased() + GROUP_DESC_MEETING1;
        descriptor = new EditMeetingDescriptorBuilder().withGroups(VALID_GROUP_MEETING1).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PRIORITY_DESC_MEETING2 + NAME_DESC_MEETING2
                + START_DESC_MEETING2 + END_DESC_MEETING2 + GROUP_DESC_MEETING2 + GROUP_DESC_MEETING1
                + PRIORITY_DESC_MEETING1 + NAME_DESC_MEETING1 + START_DESC_MEETING1 + END_DESC_MEETING1;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withPriority(VALID_PRIORITY_MEETING1)
                .withName(VALID_NAME_MEETING1)
                .withStart(VALID_START_MEETING1)
                .withTerminate(VALID_TERMINATE_MEETING1)
                .withGroups(VALID_GROUP_MEETING1, VALID_GROUP_MEETING2)
                .build();

        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PRIORITY_DESC + PRIORITY_DESC_MEETING2;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withPriority(VALID_PRIORITY_MEETING2).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_MEETING2 + INVALID_NAME_DESC + GROUP_DESC_MEETING2
                + NAME_DESC_MEETING2;
        descriptor = new EditMeetingDescriptorBuilder().withPriority(VALID_PRIORITY_MEETING2)
                .withName(VALID_NAME_MEETING2)
                .withGroups(VALID_GROUP_MEETING2).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + GROUP_EMPTY;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withGroups().build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
