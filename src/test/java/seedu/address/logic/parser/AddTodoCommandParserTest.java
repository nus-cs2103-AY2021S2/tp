package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX_STANDALONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.model.task.todo.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandParserTest {

    private AddTodoCommandParser parser = new AddTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Todo expectedTodo = new TodoBuilder().withDescription("CS2106 Tutorial").build();

        // all field appear
        assertParseSuccess(parser, INDEX_STANDALONE_ONE + VALID_DESCRIPTION,
                new AddTodoCommand(expectedProjectIndex, expectedTodo)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, VALID_DESCRIPTION, expectedMessage);

        // missing description
        assertParseFailure(parser, INDEX_STANDALONE_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, INVALID_INDEX_STANDALONE + VALID_DESCRIPTION, MESSAGE_INVALID_INDEX);

        // invalid description
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_DESCRIPTION,
                Messages.MESSAGE_PARSER_DESCRIPTION_CONSTRAINTS);
    }

}
