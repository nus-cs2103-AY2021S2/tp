package seedu.timeforwheels.logic.parser;

import static seedu.timeforwheels.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.timeforwheels.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.timeforwheels.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.logic.commands.FindCommand;
import seedu.timeforwheels.model.customer.AttributeContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AttributeContainsKeywordsPredicate(Arrays.asList("Alice")));
        assertParseSuccess(parser, "Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n\t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AttributeContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsDiffAttributes_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AttributeContainsKeywordsPredicate(Arrays.asList("Alice", "Clementi")));
        assertParseSuccess(parser, "Alice Clementi", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Clementi  \t", expectedFindCommand);
    }

    @Test
    public void parse_validRepeatArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AttributeContainsKeywordsPredicate(Arrays.asList("Alice", "Alice")));
        assertParseSuccess(parser, "Alice Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Alice  \t", expectedFindCommand);
    }

    @Test
    public void parse_validButNotPresentSingleArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AttributeContainsKeywordsPredicate(Arrays.asList("ABC")));
        assertParseSuccess(parser, "ABC", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n ABC \n \t \t", expectedFindCommand);
    }

    @Test
    public void parse_validButNotPresentMultipleArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AttributeContainsKeywordsPredicate(Arrays.asList("ABC", "DEF")));
        assertParseSuccess(parser, "ABC DEF", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n ABC \n \t DEF  \t", expectedFindCommand);
    }

}
