package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DEADLINE_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_PRIORITY;
import static seedu.heymatez.logic.commands.CommandTestUtil.ASSIGNEE_TASK2;
import static seedu.heymatez.logic.commands.CommandTestUtil.DEADLINE_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.DEADLINE_TASK2;
import static seedu.heymatez.logic.commands.CommandTestUtil.DESCRIPTION_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.DESCRIPTION_TASK2;
import static seedu.heymatez.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.heymatez.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.heymatez.logic.commands.CommandTestUtil.PRIORITY_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.PRIORITY_TASK2;
import static seedu.heymatez.logic.commands.CommandTestUtil.TITLE_DESC_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.TITLE_DESC_TASK2;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_ASSIGNEE_MEETING;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DEADLINE_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DEADLINE_MEETING;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_PRIORITY_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_PRIORITY_MEETING;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.heymatez.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.logic.commands.EditTaskCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.testutil.EditTaskDescriptorBuilder;

/**
 * Contains unit tests for {@code EditTaskCommandParser}.
 */
public class EditTaskCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "-d This is my new description",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "N", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "-h", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "0", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertParseFailure(parser, "-1", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidInteger_failure() {
        // invalid index
        assertParseFailure(parser, "0", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertParseFailure(parser, "-1", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, MESSAGE_INVALID_TASK_DEADLINE_FORMAT
                + Deadline.MESSAGE_CONSTRAINTS);

        //invalid priority
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, MESSAGE_INVALID_TASK_PRIORITY
                + Priority.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_TASK1 + DESCRIPTION_TASK1 + DEADLINE_TASK1
                + PRIORITY_TASK1;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_MARATHON)
                .withDescription(VALID_DESCRIPTION_MARATHON).withDeadline(VALID_DEADLINE_MARATHON)
                .withPriority(VALID_PRIORITY_MARATHON).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_TASK2 + DESCRIPTION_TASK2;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_MEETING)
                .withDescription(VALID_DESCRIPTION_MEETING).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_TASK2;
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_MEETING).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_TASK2;
        descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_DESCRIPTION_MEETING).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_TASK2;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_MEETING).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_TASK2;
        descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_PRIORITY_MEETING).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // assignee
        userInput = targetIndex.getOneBased() + ASSIGNEE_TASK2;
        descriptor = new EditTaskDescriptorBuilder().withAssignees(VALID_ASSIGNEE_MEETING).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() throws ParseException {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_TASK1 + DEADLINE_TASK1
                + DESCRIPTION_TASK1 + DEADLINE_TASK1 + DESCRIPTION_TASK2 + DEADLINE_TASK2;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_MEETING).withDeadline(VALID_DEADLINE_MEETING).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_TASK1;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_MARATHON).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
