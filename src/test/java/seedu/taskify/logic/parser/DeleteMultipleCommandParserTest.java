package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskify.logic.parser.ParserUtil.MESSAGE_AT_LEAST_ONE_INVALID_INDEX;
import static seedu.taskify.logic.parser.ParserUtil.MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX;
import static seedu.taskify.testutil.TypicalIndexes.INDEXES_FIRST_TO_THIRD_TASK;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.DeleteMultipleCommand;

public class DeleteMultipleCommandParserTest {

    private DeleteMultipleCommandParser parser = new DeleteMultipleCommandParser();

    @ParameterizedTest
    @ValueSource(strings = {" 1 2 3", "  1    2  3   "})
    public void parse_validArgs_returnsDeleteMultipleCommand(String input) {
        assertParseSuccess(parser, input, new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK));
    }

    @ParameterizedTest
    @ValueSource(strings = {" 1.0 2 3", " 1.0 kappa 3.0", "pogchamp 2 3.0"})
    public void parse_invalidArgs_throwsParseException(String input) {
        assertParseFailure(parser, input, MESSAGE_AT_LEAST_ONE_INVALID_INDEX);
    }

    @Test
    public void parse_onlyOneIndexAndValid_throwsParseException() {
        assertParseFailure(parser, " 1   ", MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX);
    }
}
