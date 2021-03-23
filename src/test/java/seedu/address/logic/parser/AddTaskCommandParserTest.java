package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.MARATHON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;


public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(MARATHON).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_TITLE_MARATHON + DESCRIPTION_TASK1
                + DEADLINE_TASK1, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, TITLE_DESC_TASK1 + VALID_DESCRIPTION_MARATHON + DEADLINE_TASK1,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, TITLE_DESC_TASK1 + DESCRIPTION_TASK1 + VALID_DEADLINE_MARATHON,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_MARATHON + VALID_DESCRIPTION_MARATHON + VALID_DEADLINE_MARATHON,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid deadline
        assertParseFailure(parser, VALID_TITLE_MARATHON + VALID_DESCRIPTION_MARATHON + INVALID_DEADLINE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
