package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.FindCommand;
import fooddiary.model.entry.NameContainsKeywordsPredicate;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("A", "B")));
        assertParseSuccess(parser, "A B", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n A \n \t B  \t", expectedFindCommand);
    }

}
