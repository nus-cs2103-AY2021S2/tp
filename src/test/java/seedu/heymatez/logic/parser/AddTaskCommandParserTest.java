package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.commands.CommandTestUtil.DEADLINE_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.DESCRIPTION_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.heymatez.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.heymatez.logic.commands.CommandTestUtil.TITLE_DESC_TASK1;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DEADLINE_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.heymatez.testutil.TypicalTasks.MARATHON;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.AddTaskCommand;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains unit tests for {@code AddTaskCommandParser}.
 */
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
