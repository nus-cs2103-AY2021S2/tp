package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.logic.commands.FindCommand;
import seedu.partyplanet.model.person.predicates.NameContainsKeywordsPredicate;

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
                new FindCommand(List.of(new NameContainsKeywordsPredicate("Alice")));
        assertParseSuccess(parser, " " + PREFIX_NAME + " Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t  \t", expectedFindCommand);
    }

}
