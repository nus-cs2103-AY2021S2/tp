package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.task.predicates.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.predicates.TagContainsKeywordsPredicate;
import seedu.address.model.task.predicates.TitleContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // blank argument
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));

        // blank argument after t/ prefix
        assertParseFailure(parser, "t/  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.TAG_USAGE));

        // blank argument after d/ prefix
        assertParseFailure(parser, "d/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.DESCRIPTION_USAGE));
    }

    @Test
    public void parse_findArgsWithTrailingWhitespace_failure() {
        // trailing whitespace after d/ prefix
        assertParseFailure(parser, "d/  Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.DESCRIPTION_USAGE));

        // trailing whitespace after t/ prefix
        assertParseFailure(parser, "t/  Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.TAG_USAGE));
    }

    @Test
    public void parse_multipleArgsIntoFindByTitle_throwsParseException() {
        // mix of description for find by title query
        assertParseFailure(parser, "Alice   d/Charlie", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));

        // mix of tag for find by title query
        assertParseFailure(parser, "Alice   t/exam", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));

        // mixture of d/ and t/ prefix for find by title query
        assertParseFailure(parser, "Alice  d/Bob t/exam", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
    }

    @Test
    public void parse_multipleArgsIntoFindByDescription_throwsParseException() {
        // 2 description prefix in query, only 1 should be present
        assertParseFailure(parser, "d/Bob   d/Charlie", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));

        // mix of d/ and t/ prefix
        assertParseFailure(parser, "d/Bob   t/project   t/exam", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
    }


    @Test
    public void parse_multipleArgsIntoFindByTag_throwsParseException() {
        // mix of d/ and t/ prefix in find by tag query
        assertParseFailure(parser, "t/exam  d/Bob   d/Charlie", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommandParserUtil.MULTIPLE_COMMANDS));
    }

    @Test
    public void parse_validArgs_returnsFindByTitleCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }


    @Test
    public void parse_validArgs_returnsFindByDescriptionCommand() {
        // locate description from keywords
        FindCommand expectedFindCommand =
                new FindCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        // no trailing whitespace after prefix
        assertParseSuccess(parser, "d/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords, not right after prefix
        assertParseSuccess(parser, "d/Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindByTagCommand() {
        // no leading and trailing whitespaces, single tag keyword
        FindCommand expectedFindCommand = new FindCommand(new TagContainsKeywordsPredicate(
                new HashSet<>(Arrays.asList("Bob"))));

        // single tag from keyword
        assertParseSuccess(parser, "t/Bob", expectedFindCommand);

        // no leading and trailing whitespaces, multiple tags keywords
        expectedFindCommand = new FindCommand(new TagContainsKeywordsPredicate(
                new HashSet<>(Arrays.asList("Bob", "Charlie"))));

        // no trailing whitespace after prefix, locate multiple tags from keywords
        assertParseSuccess(parser, "t/Bob t/Charlie", expectedFindCommand);

        // multiple whitespaces between keywords, not right after prefix
        assertParseSuccess(parser, "t/Charlie \n \t Bob  \t", expectedFindCommand);

        // locate multiple tags from keywords, not required to keep putting prefix for subsequent tags
        assertParseSuccess(parser, "t/Bob Charlie", expectedFindCommand);

        // tag order does not matter
        assertParseSuccess(parser, "t/Charlie t/Bob", expectedFindCommand);
    }
}
