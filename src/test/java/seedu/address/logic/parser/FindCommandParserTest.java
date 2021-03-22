package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.TagContainsKeywordsPredicate;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "t/  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.TAG_USAGE));
        assertParseFailure(parser, "d/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.DESCRIPTION_USAGE));
    }

    @Test
    public void parse_multipleFindArgs_throwsParseException() {
        assertParseFailure(parser, "d/Bob   d/Charlie", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
        assertParseFailure(parser, "d/Bob   t/project   t/exam", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
        assertParseFailure(parser, "t/exam  d/Bob   d/Charlie", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
        assertParseFailure(parser, "Alice   d/Charlie", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
        assertParseFailure(parser, "Alice   t/exam", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
        assertParseFailure(parser, "Alice  d/Bob t/exam", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);

        // locate multiple tags from keywords
        expectedFindCommand = new FindCommand(new TagContainsKeywordsPredicate(
                new HashSet<>(Arrays.asList("Bob", "Charlie"))));
        assertParseSuccess(parser, "t/Bob t/Charlie", expectedFindCommand);

        // locate description from keywords
        expectedFindCommand =
                new FindCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "d/Alice Bob", expectedFindCommand);

    }

}
