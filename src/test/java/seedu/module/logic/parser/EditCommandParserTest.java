package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.DEADLINE_DESC_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.DESCRIPTION_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_WORKLOAD_DESC;
import static seedu.module.logic.commands.CommandTestUtil.MODULE_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.MODULE_DESC_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.START_TIME_DESC_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.TAG_DESC_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.TAG_DESC_LOW;
import static seedu.module.logic.commands.CommandTestUtil.TASK_NAME_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_LOW;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_WORKLOAD_1;
import static seedu.module.logic.commands.CommandTestUtil.VALID_WORKLOAD_2;
import static seedu.module.logic.commands.CommandTestUtil.WORKLOAD_DESC_1;
import static seedu.module.logic.commands.CommandTestUtil.WORKLOAD_DESC_2;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.module.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.EditCommand;
import seedu.module.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;
import seedu.module.testutil.EditTaskDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_NAME_LAB, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASK_NAME_DESC_LAB, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASK_NAME_DESC_LAB, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid workload
        assertParseFailure(parser, "1" + INVALID_WORKLOAD_DESC, Workload.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid startTime followed by valid module
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC + MODULE_DESC_LAB, Time.MESSAGE_CONSTRAINTS);

        // valid startTime followed by invalid deadline.
        assertParseFailure(parser, "1" + START_TIME_DESC_PRACTICAL
                + INVALID_START_TIME_DESC, Time.MESSAGE_CONSTRAINTS);

        // invalid deadline followed by valid module
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC + MODULE_DESC_LAB, Time.MESSAGE_CONSTRAINTS);

        // valid deadline followed by invalid deadline. The test case for invalid deadline followed by valid deadline
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DEADLINE_DESC_PRACTICAL
                + INVALID_DEADLINE_DESC, Time.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Task} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_LOW + TAG_DESC_HIGH + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_LOW + TAG_EMPTY + TAG_DESC_HIGH, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_LOW + TAG_DESC_HIGH, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC + INVALID_MODULE_DESC
                + VALID_DESCRIPTION_LAB + VALID_DEADLINE_LAB + VALID_WORKLOAD_1, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_PRACTICAL + TAG_DESC_HIGH
                + MODULE_DESC_LAB + DESCRIPTION_DESC_LAB + TASK_NAME_DESC_LAB + WORKLOAD_DESC_1 + TAG_DESC_LOW;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_LAB)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_LAB)
                .withDescription(VALID_DESCRIPTION_LAB)
                .withWorkload(VALID_WORKLOAD_1)
                .withTags(VALID_TAG_PRIORITY_HIGH, VALID_TAG_PRIORITY_LOW).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_PRACTICAL + MODULE_DESC_LAB;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_LAB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_LAB;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_LAB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_LAB;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_LAB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module
        userInput = targetIndex.getOneBased() + MODULE_DESC_LAB;
        descriptor = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_LAB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_LAB;
        descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_LAB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // workload
        userInput = targetIndex.getOneBased() + WORKLOAD_DESC_2;
        descriptor = new EditTaskDescriptorBuilder().withWorkload(VALID_WORKLOAD_2).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_LOW;
        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_PRIORITY_LOW).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_LAB + DESCRIPTION_DESC_LAB + MODULE_DESC_LAB
                + TAG_DESC_LOW + DEADLINE_DESC_LAB + DESCRIPTION_DESC_LAB + MODULE_DESC_LAB + WORKLOAD_DESC_1
                + TAG_DESC_LOW + DEADLINE_DESC_PRACTICAL + DESCRIPTION_DESC_PRACTICAL + MODULE_DESC_PRACTICAL
                + WORKLOAD_DESC_2 + TAG_DESC_HIGH;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_PRACTICAL).withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withWorkload(VALID_WORKLOAD_2).withTags(VALID_TAG_PRIORITY_LOW, VALID_TAG_PRIORITY_HIGH)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_PRACTICAL;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_PRACTICAL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + MODULE_DESC_PRACTICAL
                + INVALID_DEADLINE_DESC
                + DESCRIPTION_DESC_PRACTICAL
                + DEADLINE_DESC_PRACTICAL;
        descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
