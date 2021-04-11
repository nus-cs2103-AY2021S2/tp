package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DoTodayCommand;
import seedu.address.logic.util.OperationFlag;

public class DoTodayCommandParserTest {

    private OperationFlag addOperationFlag = new OperationFlag(OperationFlag.ADD_FLAG);

    private OperationFlag removeOperationFlag = new OperationFlag(OperationFlag.REMOVE_FLAG);

    private DoTodayCommandParser parser = new DoTodayCommandParser();

    @Test
    public void parse_validArgs_returnsDoTodayCommand() {
        // add flag
        assertParseSuccess(parser, "-a 1", new DoTodayCommand(INDEX_FIRST_TASK, addOperationFlag));
        assertParseSuccess(parser, "-a 2", new DoTodayCommand(INDEX_SECOND_TASK, addOperationFlag));
        assertParseSuccess(parser, "-a 3", new DoTodayCommand(INDEX_THIRD_TASK, addOperationFlag));
        // remove flag
        assertParseSuccess(parser, "-r 1", new DoTodayCommand(INDEX_FIRST_TASK, removeOperationFlag));
        assertParseSuccess(parser, "-r 2", new DoTodayCommand(INDEX_SECOND_TASK, removeOperationFlag));
        assertParseSuccess(parser, "-r 3", new DoTodayCommand(INDEX_THIRD_TASK, removeOperationFlag));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // random string
        assertParseFailure(parser, "hello world",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoTodayCommand.MESSAGE_USAGE));
        // empty string
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoTodayCommand.MESSAGE_USAGE));
    }
}