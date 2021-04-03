package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.FindPersonCommand;
import seedu.booking.model.person.EmailContainsKeywordsPredicate;

public class FindPersonCommandParserTest {

    /*private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPersonCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindCommand =
                new FindPersonCommand(new EmailContainsKeywordsPredicate("jane@gmail.com"));
        assertParseSuccess(parser, " e/jane@gmail.com", expectedFindCommand);
    }*/

}
