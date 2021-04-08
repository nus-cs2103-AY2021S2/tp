package seedu.iscam.logic.parser;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.FindPlansCommand;
import seedu.iscam.logic.parser.clientcommands.FindPlansCommandParser;
import seedu.iscam.model.client.PlanContainsKeywordsPredicate;

public class FindPlansCommandParserTest {

    private FindPlansCommandParser parser = new FindPlansCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPlansCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPlansCommand expectedFindCommand =
                new FindPlansCommand(new PlanContainsKeywordsPredicate(Arrays.asList("MediShield", "Protect")));
        assertParseSuccess(parser, "MediShield Protect", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n MediShield \n \t Protect  \t", expectedFindCommand);
    }

}
