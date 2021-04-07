package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommand.FindModuleCommand;
import seedu.address.model.module.TitleContainsKeywordsPredicate;

public class FindModuleCommandParserTest {

    private FindModuleCommandParser parser = new FindModuleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPersonCommand() {
        // no leading and trailing whitespaces
        FindModuleCommand expectedFindModuleCommand =
                new FindModuleCommand(new TitleContainsKeywordsPredicate(
                        Arrays.asList("MOD1", "MOD2")));
        assertParseSuccess(parser, " m/MOD1 MOD2", expectedFindModuleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " m/\n MOD1 \n \t MOD2  \t", expectedFindModuleCommand);
    }
}
