package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.garment.ColourContainsKeywordsPredicate;
import seedu.address.model.garment.DescriptionContainsKeywordsPredicate;
import seedu.address.model.garment.DressCodeContainsKeywordsPredicate;
import seedu.address.model.garment.NameContainsKeywordsPredicate;
import seedu.address.model.garment.SizeContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validSizeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new SizeContainsKeywordsPredicate(Arrays.asList("22", "10")));
        assertParseSuccess(parser, " s/22 10", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " s/\n 22 \n \t 10  \t", expectedFindCommand);
    }

    @Test
    public void parse_validColourArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ColourContainsKeywordsPredicate(Arrays.asList("red", "black")));
        assertParseSuccess(parser, " c/red black", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " c/\n red \n \t black  \t", expectedFindCommand);
    }

    @Test
    public void parse_validDressCodeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new DressCodeContainsKeywordsPredicate(Arrays.asList("ACTIVE", "CASUAL")));
        assertParseSuccess(parser, " n/ACTIVE CASUAL", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n ACTIVE \n \t CASUAL  \t", expectedFindCommand);
    }

    //test issue desc more than one
    @Test
    public void parse_validDescriptionsArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("husband")));
        assertParseSuccess(parser, " d/husband", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " d/\n husband \n  \t", expectedFindCommand);
    }

}
