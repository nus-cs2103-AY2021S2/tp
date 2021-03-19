package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.taskify.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.taskify.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.taskify.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.taskify.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taskify.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taskify.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.taskify.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskify.testutil.TypicalTasks.AMY;
import static seedu.taskify.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.taskify.logic.commands.AddCommand;
import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Description;
import seedu.taskify.model.task.Name;
import seedu.taskify.model.task.Task;
import seedu.taskify.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                        + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                         + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple description - last description accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB
                         + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                        + DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DESCRIPTION_DESC_AMY
                        + DATE_DESC_AMY, new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DESCRIPTION_DESC_BOB
                        + DATE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DESCRIPTION_BOB
                        + DATE_DESC_BOB, expectedMessage);


        // missing date prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                        + VALID_DATE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DESCRIPTION_BOB
                        + VALID_NAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_BOB
                        + DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DESCRIPTION_DESC
                        + DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);


        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                        + INVALID_DATE_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                        + DATE_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DESCRIPTION_DESC_BOB
                        + DATE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                         + DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
