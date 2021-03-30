package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.FindContentCommand;
import seedu.dictionote.model.dictionary.ContentContainsKeywordsPredicate;

public class FindContentCommandParserTest {

    private FindContentCommandParser parser = new FindContentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindContentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindContentCommand() {
        // no leading and trailing whitespaces
        FindContentCommand expectedFindContentCommand =
                new FindContentCommand(
                        new ContentContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindContentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob ", expectedFindContentCommand);
    }

}
