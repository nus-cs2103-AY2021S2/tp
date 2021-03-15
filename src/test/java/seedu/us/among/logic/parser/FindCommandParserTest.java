package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.FindCommand;
import seedu.us.among.model.endpoint.EndPointContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
                new EndPointContainsKeywordsPredicate(Arrays.asList("GET", "POST")));
        assertParseSuccess(parser, "GET POST", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n GET \n \t POST  \t", expectedFindCommand);
    }

}
