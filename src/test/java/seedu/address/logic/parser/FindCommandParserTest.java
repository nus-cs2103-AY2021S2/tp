package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.passenger.AddressContainsKeywordsPredicate;
import seedu.address.model.person.passenger.NameContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_namePrefixEmptyArg_throwsParseException() {
        assertParseFailure(parser,
                PREFIX_NAME + "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiPrefix_throwsParseException() {
        String userInput = FindCommand.COMMAND_WORD + " " + "n/Alice a/Bedok";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy")));
        assertParseSuccess(parser, " n/Amy", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Amy \n \t", expectedFindCommand);
    }

    @Test
    public void parse_validNameMultiArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy", "Bob")));
        assertParseSuccess(parser, " n/Amy n/Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Amy n/Bob\n \t", expectedFindCommand);
    }

    @Test
    public void parse_validNameArgWithWhitespace_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy Jane")));
        assertParseSuccess(parser, " n/Amy Jane", expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(VALID_PHONE_AMY)));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + PHONE_DESC_AMY + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, PHONE_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(VALID_ADDRESS_AMY)));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + ADDRESS_DESC_AMY + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, ADDRESS_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("[" + VALID_TAG_FRIEND + "]")));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + TAG_DESC_FRIEND + "\n \t", expectedFindCommand);

        assertParseSuccess(parser, TAG_DESC_FRIEND, expectedFindCommand);
    }
}
