package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.MESSAGE_AT_LEAST_ONE_INVALID_INDEX;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.MESSAGE_DELETE_BY_STATUS_USAGE;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taskify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taskify.testutil.Assert.assertThrows;
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
    public void parse_onlyOneIndexAndValid_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> parser.parse("  1 "));
    }


    @ParameterizedTest
    @ValueSource(strings = {" expired  -all", " completed   -all", "uncompleted -all    "})
    public void parse_deleteByStatusAndArgsValid_returnsDeleteMultipleCommand(String input) {
        switch (input) {
        case " expired  -all":
            assertParseSuccess(parser, input, new DeleteMultipleCommand(new Status(StatusType.EXPIRED)));
            break;
        case " completed   -all":
            assertParseSuccess(parser, input, new DeleteMultipleCommand(new Status(StatusType.COMPLETED)));
            break;
        case "uncompleted -all    ":
            assertParseSuccess(parser, input, new DeleteMultipleCommand(new Status(StatusType.UNCOMPLETED)));
            break;
        default:
            assert false;
        }
    }




    // need to extend testing for this for more rogue inputs like "... ---all", in v1.4
    @ParameterizedTest
    @ValueSource(strings = {" expired all", "uncompleted --all", "Completed -all"})
    public void parse_deleteByStatusAndArgsInvalid_throwsParseException(String input) {
        assertParseFailure(parser, input, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_DELETE_BY_STATUS_USAGE));
    }
}
