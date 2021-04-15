package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateTodoCommand;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;

public class UpdateTodoCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTodoCommand.MESSAGE_USAGE);

    private UpdateTodoCommandParser parser = new UpdateTodoCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no project index specified
        assertParseFailure(parser, "i/1" + VALID_DESCRIPTION, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateTodoCommand.MESSAGE_USAGE));

        // no todo index specified
        assertParseFailure(parser, "1" + VALID_DESCRIPTION, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateTodoCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateTodoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + " i/1" + VALID_DESCRIPTION, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + " i/1" + VALID_DESCRIPTION, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + " i/1" + VALID_DESCRIPTION,
                MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidTodoIndex_failure() {
        // negative index
        assertParseFailure(parser, VALID_INDEX_ONE + " i/-5" + VALID_DESCRIPTION,
                MESSAGE_INVALID_TODO_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, VALID_INDEX_ONE + " i/0" + VALID_DESCRIPTION,
                MESSAGE_INVALID_TODO_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1 some random string" + VALID_DESCRIPTION,
                MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidDescription_failure() {
        assertParseFailure(parser, VALID_INDEX_ONE + " i/1" + INVALID_DESCRIPTION,
                MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldSpecifiedAndValid_success() {
        String userInput = VALID_INDEX_ONE + " i/1" + VALID_DESCRIPTION;
        CompletableTodo todo = new Todo("CS2106 Tutorial");
        UpdateTodoCommand expectedCommand = new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST, todo);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
