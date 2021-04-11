package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.util.SortingFlag;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();
    private SortingFlag dateTimeFlag = new SortingFlag("dateTime");
    private SortingFlag moduleCodeFlag = new SortingFlag("moduleCode");
    private SortingFlag priorityTagFlag = new SortingFlag("priorityTag");
    private SortingFlag taskNameFlag = new SortingFlag("taskName");
    private SortingFlag weightageFlag = new SortingFlag("weightage");

    @Test
    public void parse_validArgsDateTimeFlag_returnsSortCommand() {
        assertParseSuccess(parser, "dateTime", new SortCommand(dateTimeFlag));
    }

    @Test
    public void parse_validArgsModuleCodeFlag_returnsSortCommand() {
        assertParseSuccess(parser, "moduleCode", new SortCommand(moduleCodeFlag));
    }

    @Test
    public void parse_validArgsPriorityTagFlag_returnsSortCommand() {
        assertParseSuccess(parser, "priorityTag", new SortCommand(priorityTagFlag));
    }

    @Test
    public void parse_validArgsTaskNameFlag_returnsSortCommand() {
        assertParseSuccess(parser, "taskName", new SortCommand(taskNameFlag));
    }

    @Test
    public void parse_validArgsWeightageFlag_returnsSortCommand() {
        assertParseSuccess(parser, "weightage", new SortCommand(weightageFlag));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortingFlag.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidArgsNoArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
