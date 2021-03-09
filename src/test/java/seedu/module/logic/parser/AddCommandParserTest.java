package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.module.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.module.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.module.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.module.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.module.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.module.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.module.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.module.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.module.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.module.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.module.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalTasks.AMY;
import static seedu.module.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.AddCommand;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Deadline;
import seedu.module.model.task.Description;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_AMY + DEADLINE_DESC_BOB + MODULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_AMY + MODULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple descriptiones - last description accepted
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB + DESCRIPTION_DESC_AMY
                + DESCRIPTION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + DEADLINE_DESC_AMY + MODULE_DESC_AMY + DESCRIPTION_DESC_AMY,
                new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DEADLINE_BOB + MODULE_DESC_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + VALID_MODULE_BOB + DESCRIPTION_DESC_BOB,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB + VALID_DESCRIPTION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DEADLINE_BOB + VALID_MODULE_BOB + VALID_DESCRIPTION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_BOB + MODULE_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DEADLINE_DESC + MODULE_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Deadline.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + INVALID_MODULE_DESC + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Module.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB + INVALID_DESCRIPTION_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB + DESCRIPTION_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_BOB + MODULE_DESC_BOB + INVALID_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DEADLINE_DESC_BOB + MODULE_DESC_BOB
                + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
