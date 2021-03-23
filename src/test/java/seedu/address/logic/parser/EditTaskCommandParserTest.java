package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Priority;
import seedu.address.testutil.EditTaskDescriptorBuilder;

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
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        //invalid priority
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);
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
