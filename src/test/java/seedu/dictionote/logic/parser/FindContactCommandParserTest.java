package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.FindContactCommand;
import seedu.dictionote.model.contact.EmailContainsKeywordsPredicate;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.model.contact.TagsContainKeywordsPredicate;

public class FindContactCommandParserTest {

    private FindContactCommandParser parser = new FindContactCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindContactCommand() {
        // no leading and trailing whitespaces
        FindContactCommand expectedFindContactCommand =
                new FindContactCommand(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new EmailContainsKeywordsPredicate(Arrays.asList("@a.com", "@b.net")),
                        new TagsContainKeywordsPredicate(Arrays.asList("A", "B"))
                        );
        assertParseSuccess(parser, " n/Alice n/Bob e/@a.com e/@b.net t/A t/B", expectedFindContactCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(
                parser,
                " \n n/Alice \n \t n/Bob e/@a.com  \t e/@b.net \n t/A \t t/B",
                expectedFindContactCommand
        );
    }

}
