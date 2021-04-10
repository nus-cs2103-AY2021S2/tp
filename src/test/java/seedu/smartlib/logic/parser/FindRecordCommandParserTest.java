package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartlib.logic.commands.FindRecordCommand;
import seedu.smartlib.model.record.RecordContainsBookNamePredicate;


public class FindRecordCommandParserTest {
    private FindRecordCommandParser parser = new FindRecordCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindRecordCommand expectedFindRecordCommand =
                new FindRecordCommand(new RecordContainsBookNamePredicate(Arrays.asList("Harry", "Potter")));
        assertParseSuccess(parser, "Harry Potter", expectedFindRecordCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Harry \n \t Potter  \t", expectedFindRecordCommand);
    }

}
