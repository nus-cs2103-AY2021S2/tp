package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRINGSCHEDULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRINGSCHEDULE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RECURRINGSCHEDULE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_FRIEND;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalTasks.AMY;
import static seedu.address.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.conditions.ConstraintManager;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Title;
import seedu.address.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_BOB + DATE_DESC_BOB
                + DURATION_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_AMY + TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple dates - last date accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_DESC_AMY + DATE_DESC_BOB + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple durations - last duration accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_AMY + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple recurringTasks - last recurringTasks accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTask));

        // multiple description - last description accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
                + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + DATE_DESC_AMY + DURATION_DESC_AMY + RECURRINGSCHEDULE_DESC_AMY
                        + STATUS_DESC_AMY + DESCRIPTION_DESC_AMY, new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ConstraintManager.MESSAGE_EMPTY_TITLE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_BOB + DATE_DESC_BOB + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DATE_DESC_BOB + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Title.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, TITLE_DESC_BOB + INVALID_DATE_DESC + DURATION_DESC_BOB
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Date.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_DESC_BOB + INVALID_DURATION_DESC
                + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Duration.MESSAGE_CONSTRAINTS);

        // invalid recurringSchedule
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB
                + INVALID_RECURRINGSCHEDULE_DESC + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, RecurringSchedule.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB + RECURRINGSCHEDULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_BOB + DATE_DESC_BOB + DURATION_DESC_BOB
                        + RECURRINGSCHEDULE_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                AddCommandParser.MESSAGE_PREAMBLE_PARSED);
    }
}
