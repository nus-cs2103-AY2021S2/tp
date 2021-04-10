package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskify.testutil.Assert.assertThrows;
import static seedu.taskify.testutil.TypicalIndexes.INDEXES_FIRST_TO_THIRD_TASK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import seedu.taskify.logic.commands.DeleteCommand;
import seedu.taskify.logic.commands.DeleteMultipleCommand;

public class DeleteMultipleCommandParserTest {

    private DeleteMultipleCommandParser parser = new DeleteMultipleCommandParser();

    @ParameterizedTest
    @ValueSource(strings = {" 1 2 3", "  1    2  3   ", " 1-3 "})
    public void parse_validArgs_returnsDeleteMultipleCommand(String input) {
        assertParseSuccess(parser, input, new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK, false));
    }

    @ParameterizedTest
    @ValueSource(strings = {" 1.0 2 3", " 1.0 kappa 3.0", "pogchamp 2 3.0", "100to 101"})
    public void parse_atLeastOneInvalidIndex_throwsParseException(String input) {
        assertParseFailure(parser, input, String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_onlyOneIndexAndValid_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> parser.parse("  1 "));
    }

}
