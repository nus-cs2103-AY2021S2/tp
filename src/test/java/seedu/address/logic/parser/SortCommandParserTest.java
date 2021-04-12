package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskDateComparator;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_invalidSort_failure() {
        assertParseFailure(parser, "by h", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "by/ a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "by/ d", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingSort_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParameters_failure() {
        assertParseFailure(parser, "sort", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAscendingSort_success() {
        String validSort = " by a";
        TaskDateComparator expectedPredicate = new TaskDateComparator();
        SortCommand expectedCommand = new SortCommand(expectedPredicate, true);
        try {
            assertEquals(new SortCommand(expectedPredicate, true), parser.parse(validSort));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertParseSuccess(parser, validSort, expectedCommand);
    }

    @Test
    public void parse_validDescendingSort_success() {
        String validSort = " by d";
        TaskDateComparator expectedPredicate = new TaskDateComparator();
        SortCommand expectedCommand = new SortCommand(expectedPredicate, false);
        try {
            assertEquals(new SortCommand(expectedPredicate, false), parser.parse(validSort));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertParseSuccess(parser, validSort, expectedCommand);
    }

}
