package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartlib.logic.commands.FindBookCommand;
import seedu.smartlib.model.book.BookNameContainsKeywordsPredicate;

public class FindBookCommandParserTest {

    private FindBookCommandParser parser = new FindBookCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindBookCommand expectedFindBookCommand =
                new FindBookCommand(new BookNameContainsKeywordsPredicate(Arrays.asList("Harry", "Potter")));
        assertParseSuccess(parser, "Harry Potter", expectedFindBookCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Harry \n \t Potter  \t", expectedFindBookCommand);
    }

}
