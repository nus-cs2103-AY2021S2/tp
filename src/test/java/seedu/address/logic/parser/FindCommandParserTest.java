package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.FieldsContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyFlags_throwsParseException() {
        assertParseFailure(parser,
                "-n Alice -t Reception",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));

        FindCommand expectedNameFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser,
                PREFIX_NAME + PREAMBLE_WHITESPACE + "Alice Bob", expectedNameFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                PREFIX_NAME + PREAMBLE_WHITESPACE + " \n Alice \n \t Bob  \t", expectedNameFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("Hotline", "Service"));

        FindCommand expectedTagFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser,
                PREFIX_TAG + PREAMBLE_WHITESPACE + "Hotline Service", expectedTagFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                PREFIX_TAG + PREAMBLE_WHITESPACE + " \n Hotline \n \t Service  \t", expectedTagFindCommand);
    }

    @Test
    public void parse_validRemarkArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        RemarkContainsKeywordsPredicate predicate =
                new RemarkContainsKeywordsPredicate(Arrays.asList("HR", "Network"));

        FindCommand expectedRemarkFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser,
                PREFIX_REMARK + PREAMBLE_WHITESPACE + "HR Network", expectedRemarkFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                PREFIX_REMARK + PREAMBLE_WHITESPACE + " \n HR \n \t Network  \t", expectedRemarkFindCommand);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Arrays.asList("test@mail.com", "bob@box.net"));

        FindCommand expectedEmailFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser,
                PREFIX_EMAIL + PREAMBLE_WHITESPACE
                        + "test@mail.com bob@box.net", expectedEmailFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                PREFIX_EMAIL + PREAMBLE_WHITESPACE
                        + " \n test@mail.com \n \t bob@box.net  \t", expectedEmailFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FieldsContainsKeywordsPredicate predicate =
                new FieldsContainsKeywordsPredicate(Arrays.asList("Alice", "Reception"));

        FindCommand expectedFieldsFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, "Alice Reception", expectedFieldsFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Reception  \t", expectedFieldsFindCommand);
    }

}
