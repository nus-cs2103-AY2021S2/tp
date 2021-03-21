package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRINGSCHEDULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STARTTIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRINGSCHEDULE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RECURRINGSCHEDULE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STARTTIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_BOB;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.AMY;
import static seedu.address.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_AMY + TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DEADLINE_DESC_AMY + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple startTimes - last startTime accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_AMY + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple recurringTasks - last recurringTasks accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTask));

        // multiple description - last description accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
                + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + DEADLINE_DESC_AMY + STARTTIME_DESC_AMY + RECURRINGSCHEDULE_DESC_AMY
                        + STATUS_DESC_AMY + DESCRIPTION_DESC_AMY, new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB, expectedMessage);

        /*
        // missing deadline prefix
        assertParseFailure(parser, TITLE_DESC_BOB + VALID_DEADLINE_BOB + RECURRINGSCHEDULE_DESC_BOB
            + DESCRIPTION_DESC_BOB, expectedMessage);

        // missing recurring schedule prefix
        assertParseFailure(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + VALID_RECURRINGSCHEDULE_BOB
            + DESCRIPTION_DESC_BOB, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
            + VALID_DESCRIPTION_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_BOB + VALID_DEADLINE_BOB + VALID_RECURRINGSCHEDULE_BOB
            + VALID_DESCRIPTION_BOB, expectedMessage);
        */
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Title.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TITLE_DESC_BOB + INVALID_DEADLINE_DESC + STARTTIME_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Deadline.MESSAGE_CONSTRAINTS);

        // invalid starttime
        assertParseFailure(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + INVALID_STARTTIME_DESC
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                StartTime.MESSAGE_CONSTRAINTS);

        // invalid recurringSchedule
        assertParseFailure(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                + INVALID_RECURRINGSCHEDULE_DESC + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, RecurringSchedule.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // description is no longer invalid as it accepts anything
        /*
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_DESCRIPTION_DESC, Title.MESSAGE_CONSTRAINTS);
         */

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_BOB + DEADLINE_DESC_BOB + STARTTIME_DESC_BOB
                        + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
