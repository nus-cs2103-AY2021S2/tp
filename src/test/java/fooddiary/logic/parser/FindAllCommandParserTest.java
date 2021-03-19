package fooddiary.logic.parser;

import fooddiary.logic.commands.FindAllCommand;
import fooddiary.model.entry.NameContainsAllKeywordsPredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindAllCommandParserTest {

    private FindAllCommandParser parser = new FindAllCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAllCommand expectedFindCommand =
                new FindAllCommand(new NameContainsAllKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
