package seedu.taskify.logic.parser;

import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.MESSAGE_AT_LEAST_ONE_INVALID_INDEX;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskify.testutil.TypicalIndexes.INDEXES_FIRST_TO_THIRD_TASK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import seedu.taskify.logic.commands.DeleteMultipleCommand;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;

public class DeleteMultipleCommandParserTest {

    private DeleteMultipleCommandParser parser = new DeleteMultipleCommandParser();

    @ParameterizedTest
    @ValueSource(strings = {" 1 2 3", "  1    2  3   ", " 1-3 "})
    public void parse_validArgs_returnsDeleteMultipleCommand(String input) {
        assertParseSuccess(parser, input, new DeleteMultipleCommand(INDEXES_FIRST_TO_THIRD_TASK));
    }

    @ParameterizedTest
    @ValueSource(strings = {" 1.0 2 3", " 1.0 kappa 3.0", "pogchamp 2 3.0", "0-1", "100to 101"})
    public void parse_atLeastOneInvalidIndex_throwsParseException(String input) {
        assertParseFailure(parser, input, MESSAGE_AT_LEAST_ONE_INVALID_INDEX);
    }


    @Test
    public void parse_onlyOneIndexAndValid_throwsParseException() {
        assertParseFailure(parser, " 1   ", MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX);
    }

    @Test
    public void parse_deleteByStatusAndArgsValid_returnsDeleteMultipleCommand() {
        assertParseSuccess(parser, " in progress  -all", new DeleteMultipleCommand(new Status(StatusType.IN_PROGRESS)));
    }
}
