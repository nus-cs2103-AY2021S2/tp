package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASKONE;
import static seedu.address.testutil.TypicalTasks.TASKTWO;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;


public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASKTWO).withTags(VALID_TAG_DIFFICULT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO
                + PRIORITY_DESC_TASKTWO + CATEGORY_DESC_PROJECT + TAG_DESC_DIFFICULT, new AddTaskCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_TASKONE + NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO
                + PRIORITY_DESC_TASKTWO + CATEGORY_DESC_PROJECT + TAG_DESC_DIFFICULT, new AddTaskCommand(expectedTask));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKONE + DEADLINE_DESC_TASKTWO
                + PRIORITY_DESC_TASKTWO + CATEGORY_DESC_PROJECT + TAG_DESC_DIFFICULT, new AddTaskCommand(expectedTask));

        // multiple priorities - last priority accepted
        assertParseSuccess(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKONE
                + PRIORITY_DESC_TASKTWO + CATEGORY_DESC_PROJECT + TAG_DESC_DIFFICULT, new AddTaskCommand(expectedTask));

        // multiple categories - all accepted
        Task expectedTaskMultipleCategories = new TaskBuilder(TASKTWO)
                .withCategories(VALID_CATEGORY_HOMEWORK, VALID_CATEGORY_PROJECT)
                .build();
        assertParseSuccess(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_PROJECT + CATEGORY_DESC_HOMEWORK + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT,
                new AddTaskCommand(expectedTaskMultipleCategories));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(TASKTWO).withTags(VALID_TAG_IMPORTANT, VALID_TAG_DIFFICULT)
                .build();
        assertParseSuccess(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_PROJECT + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT,
                new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(TASKONE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_TASKONE + DEADLINE_DESC_TASKONE + PRIORITY_DESC_TASKONE
                        + CATEGORY_DESC_HOMEWORK, new AddTaskCommand(expectedTask));
    }

    @Test
    @Disabled
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                        + CATEGORY_DESC_HOMEWORK, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_TASKTWO + VALID_DEADLINE_TASKTWO + PRIORITY_DESC_TASKTWO
                        + CATEGORY_DESC_HOMEWORK, expectedMessage);

        // missing priority prefix
        assertParseFailure(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + VALID_PRIORITY_TASKTWO
                        + CATEGORY_DESC_HOMEWORK, expectedMessage);

        // missing category prefix
        assertParseFailure(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                        + VALID_CATEGORY_HOMEWORK, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_TASKTWO + VALID_DEADLINE_TASKTWO + VALID_PRIORITY_TASKTWO
                        + VALID_CATEGORY_HOMEWORK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_HOMEWORK + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT, Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_TASKTWO + INVALID_DEADLINE_DESC + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_HOMEWORK + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT, Date.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + INVALID_PRIORITY_DESC
                + CATEGORY_DESC_HOMEWORK + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT, Priority.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + INVALID_CATEGORY_DESC + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT, Category.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO + PRIORITY_DESC_TASKTWO
                + CATEGORY_DESC_HOMEWORK + INVALID_TAG_DESC + VALID_TAG_IMPORTANT, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_TASKTWO + INVALID_PRIORITY_DESC
                        + CATEGORY_DESC_HOMEWORK, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_TASKTWO + DEADLINE_DESC_TASKTWO
                        + PRIORITY_DESC_TASKTWO + CATEGORY_DESC_HOMEWORK + TAG_DESC_IMPORTANT + TAG_DESC_DIFFICULT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }

}
