package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalTasks.LAB;
import static seedu.module.testutil.TypicalTasks.PRACTICAL;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.AddCommand;
import seedu.module.logic.commands.CommandTestUtil;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Task;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;
import seedu.module.testutil.TaskBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(PRACTICAL)
                .withRecurrence(CommandTestUtil.VALID_RECURRENCE_PRACTICAL)
                .withTags(CommandTestUtil.VALID_TAG_PRIORITY_LOW).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE
                        + CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_LAB
                        + CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple startTime - last startTime accepted
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_LAB
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_LAB
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple modules - last module accepted
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_LAB
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_LAB
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple workloads - last workload accepted
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_1
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(PRACTICAL)
                .withRecurrence(CommandTestUtil.VALID_RECURRENCE_PRACTICAL)
                .withTags(CommandTestUtil.VALID_TAG_PRIORITY_LOW, CommandTestUtil.VALID_TAG_PRIORITY_HIGH).build();
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.START_TIME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.RECURRENCE_DESC_PRACTICAL
                        + CommandTestUtil.TAG_DESC_HIGH
                        + CommandTestUtil.TAG_DESC_LOW,
                new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // missing startTime
        Task expectedTaskWithoutStartTime = new TaskBuilder(LAB)
                .withTags(CommandTestUtil.VALID_TAG_PRIORITY_HIGH).build();
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_LAB
                        + CommandTestUtil.DEADLINE_DESC_LAB
                        + CommandTestUtil.MODULE_DESC_LAB
                        + CommandTestUtil.DESCRIPTION_DESC_LAB
                        + CommandTestUtil.WORKLOAD_DESC_1
                        + CommandTestUtil.TAG_DESC_HIGH,
                new AddCommand(expectedTaskWithoutStartTime));

        // zero tags
        Task expectedTaskWithStartTime = new TaskBuilder(LAB).activateStartTime(CommandTestUtil.VALID_START_TIME_LAB)
                .withRecurrence(CommandTestUtil.VALID_RECURRENCE_LAB).withTags().build();
        assertParseSuccess(parser,
                CommandTestUtil.TASK_NAME_DESC_LAB
                        + CommandTestUtil.START_TIME_DESC_LAB
                        + CommandTestUtil.DEADLINE_DESC_LAB
                        + CommandTestUtil.MODULE_DESC_LAB
                        + CommandTestUtil.DESCRIPTION_DESC_LAB
                        + CommandTestUtil.WORKLOAD_DESC_1
                        + CommandTestUtil.RECURRENCE_DESC_LAB,
                new AddCommand(expectedTaskWithStartTime));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                CommandTestUtil.VALID_TASK_NAME_LAB
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.VALID_DEADLINE_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2, expectedMessage);

        // missing module prefix
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.VALID_MODULE_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.VALID_DESCRIPTION_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2,
                expectedMessage);

        // missing workload prefix
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.VALID_WORKLOAD_2,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                CommandTestUtil.VALID_TASK_NAME_LAB
                        + CommandTestUtil.VALID_DEADLINE_PRACTICAL
                        + CommandTestUtil.VALID_MODULE_PRACTICAL
                        + CommandTestUtil.VALID_DESCRIPTION_PRACTICAL
                        + CommandTestUtil.VALID_WORKLOAD_2,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                CommandTestUtil.INVALID_TASK_NAME_DESC
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.TAG_DESC_HIGH
                        + CommandTestUtil.TAG_DESC_LOW, Name.MESSAGE_CONSTRAINTS);

        // invalid startTime
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.INVALID_START_TIME_DESC
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.TAG_DESC_HIGH + CommandTestUtil.TAG_DESC_LOW,
                Time.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.INVALID_DEADLINE_DESC
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.TAG_DESC_HIGH + CommandTestUtil.TAG_DESC_LOW,
                Time.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.INVALID_MODULE_DESC
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.TAG_DESC_HIGH
                        + CommandTestUtil.TAG_DESC_LOW,
                Module.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.INVALID_DESCRIPTION_DESC
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.TAG_DESC_HIGH
                        + CommandTestUtil.TAG_DESC_LOW, Description.MESSAGE_CONSTRAINTS);

        // invalid workload
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.INVALID_WORKLOAD_DESC
                        + CommandTestUtil.TAG_DESC_HIGH
                        + CommandTestUtil.TAG_DESC_LOW, Workload.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
                CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.INVALID_TAG_DESC
                        + CommandTestUtil.VALID_TAG_PRIORITY_LOW,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                CommandTestUtil.INVALID_TASK_NAME_DESC
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.INVALID_DESCRIPTION_DESC
                        + CommandTestUtil.WORKLOAD_DESC_2,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.TASK_NAME_DESC_PRACTICAL
                        + CommandTestUtil.DEADLINE_DESC_PRACTICAL
                        + CommandTestUtil.MODULE_DESC_PRACTICAL
                        + CommandTestUtil.DESCRIPTION_DESC_PRACTICAL
                        + CommandTestUtil.WORKLOAD_DESC_2
                        + CommandTestUtil.TAG_DESC_HIGH
                        + CommandTestUtil.TAG_DESC_LOW,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
