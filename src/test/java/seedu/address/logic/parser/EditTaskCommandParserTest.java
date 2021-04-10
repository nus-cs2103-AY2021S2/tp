package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PAST_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINEPAST_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC_0229;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_IMPORTANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.Priority;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_NAME_TASKTWO, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TASK_NAME_TASKTWO, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TASK_NAME_TASKTWO, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Date.MESSAGE_CONSTRAINTS_FORMAT);

        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC_0229, Date.MESSAGE_CONSTRAINTS);

        // past deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINEPAST_DESC, MESSAGE_PAST_DEADLINE);

        // invalid priority
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid deadline followed by valid priority.
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC + PRIORITY_DESC_TASKONE,
                Date.MESSAGE_CONSTRAINTS_FORMAT);

        // valid deadline followed by invalid deadline.
        assertParseFailure(parser, "1" + DEADLINE_DESC_TASKONE + INVALID_DEADLINE_DESC,
                Date.MESSAGE_CONSTRAINTS_FORMAT);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Task} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser,
                "1" + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + TAG_DESC_IMPORTANT + TAG_EMPTY + TAG_DESC_DIFFICULT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + TAG_EMPTY + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEADLINE_DESC
                + VALID_TASK_PRIORITY_TASKTWO, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASKTWO
                + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_PROJECT + CATEGORY_DESC_HOMEWORK
                + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withName(VALID_TASK_NAME_TASKTWO)
                .withDeadline(VALID_TASK_DEADLINE_TASKTWO)
                .withPriority(VALID_TASK_PRIORITY_TASKTWO)
                .withCategories(VALID_TASK_CATEGORY_PROJECT, VALID_TASK_CATEGORY_HOMEWORK)
                .withTags(VALID_TASK_TAG_IMPORTANT, VALID_TASK_TAG_DIFFICULT).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_TASKONE + PRIORITY_DESC_TASKONE;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_TASK_DEADLINE_TASKONE)
                .withPriority(VALID_TASK_PRIORITY_TASKONE)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASKTWO;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withName(VALID_TASK_NAME_TASKTWO)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_TASKTWO;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_TASK_DEADLINE_TASKTWO).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_TASKTWO;
        descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_TASK_PRIORITY_TASKTWO).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // categories
        userInput = targetIndex.getOneBased() + CATEGORY_DESC_HOMEWORK;
        descriptor = new EditTaskDescriptorBuilder().withCategories(VALID_TASK_CATEGORY_HOMEWORK).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_DIFFICULT;
        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TASK_TAG_DIFFICULT).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASKTWO
                + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_PROJECT + TAG_DESC_IMPORTANT + NAME_DESC_TASKONE
                + DEADLINE_DESC_TASKONE + PRIORITY_DESC_TASKONE
                + CATEGORY_DESC_PROJECT + CATEGORY_DESC_HOMEWORK + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withName(VALID_TASK_NAME_TASKONE)
                .withDeadline(VALID_TASK_DEADLINE_TASKONE).withPriority(VALID_TASK_PRIORITY_TASKONE)
                .withCategories(VALID_TASK_CATEGORY_PROJECT, VALID_TASK_CATEGORY_HOMEWORK)
                .withTags(VALID_TASK_TAG_IMPORTANT, VALID_TASK_TAG_DIFFICULT)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_TASKONE;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_TASK_DEADLINE_TASKONE)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_TASKONE + INVALID_PRIORITY_DESC + DEADLINE_DESC_TASKONE
                + PRIORITY_DESC_TASKONE;
        descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_TASKONE)
                .withDeadline(VALID_TASK_DEADLINE_TASKONE)
                .withPriority(VALID_TASK_PRIORITY_TASKONE)
                .build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags().build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
