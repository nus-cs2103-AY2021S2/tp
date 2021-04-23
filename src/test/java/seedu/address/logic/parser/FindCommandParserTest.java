package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_OPTION;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_OPTION_ARGS;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_ADDRESS_ARGS;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_EMAIL_ARGS;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_FIND_OPTION;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_NAME_ARGS;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_PHONE_ARGS;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_TAG_ARGS;
import static seedu.address.logic.commands.FindCommand.MESSAGE_MISSING_TAG_PREFIX;
import static seedu.address.logic.commands.FindCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.AnyContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.contact.predicate.TagsMatchKeywordPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AnyContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);

    }

    @Test
    public void parse_validOptionValidArgs_returnsFindCommand() throws ParseException {

        // option name valid args
        FindCommand expectedNameFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " o/name Alice Bob", expectedNameFindCommand);

        // option name valid args with whitespace
        FindCommand expectedNameSpaceFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " o/name Alice \n \t \tBob", expectedNameSpaceFindCommand);

        // option address valid args
        FindCommand expectedAddressFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Town", "17")));
        assertParseSuccess(parser, " o/address Town 17", expectedAddressFindCommand);

        // option phone valid args
        FindCommand expectedPhoneFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("987", "123")));
        assertParseSuccess(parser, " o/phone 987 123", expectedPhoneFindCommand);

        // option email valid args
        FindCommand expectedEmailFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("gmail", "yahoo")));
        assertParseSuccess(parser, " o/email gmail yahoo", expectedEmailFindCommand);

        // option tag valid args
        Set<Tag> tagSet = ParserUtil.parseTags(Arrays.asList("math", "science"));
        FindCommand expectedTagFindCommand =
                new FindCommand(new TagsMatchKeywordPredicate(tagSet));
        assertParseSuccess(parser, " o/tag t/math t/science", expectedTagFindCommand);

    }

    @Test
    public void parse_validOptionNoArgs_returnsFindCommand() {
        // option name no args with whitespace
        assertParseFailure(parser, " o/\nname\n \t  \n",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_NAME_ARGS));

        // option address no args
        assertParseFailure(parser, " o/address",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_ADDRESS_ARGS));

        // option phone no args
        assertParseFailure(parser, " o/phone",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_PHONE_ARGS));

        // option email no args
        assertParseFailure(parser, " o/email",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_EMAIL_ARGS));

        // option tag no args
        assertParseFailure(parser, " o/tag",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_TAG_ARGS));

    }

    @Test
    public void parse_invalidOption_returnsFindCommand() {
        // words with whitespace before option prefix but valid option
        assertParseFailure(parser, " invalidWord o/name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // words before option prefix but valid option
        assertParseFailure(parser, " invalidWordo/name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // no option after option prefix
        assertParseFailure(parser, " o/",
                String.format(MESSAGE_MISSING_OPTION, MESSAGE_MISSING_FIND_OPTION));

        // no option with whitespaces after option prefix
        assertParseFailure(parser, " o/\n \t   ",
                String.format(MESSAGE_MISSING_OPTION, MESSAGE_MISSING_FIND_OPTION));

        // invalid option valid args
        assertParseFailure(parser, " o/invalidOption dummy args",
                String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));

        // invalid option no args
        assertParseFailure(parser, " o/invalidOption",
                String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));

        // invalid option no args with whitespaces
        assertParseFailure(parser, " o/invalidOption \n  \t",
                String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));


    }

    @Test
    public void parse_optionTagMissingTagPrefix_returnsFindCommand() {
        // missing t/ in find by tag
        assertParseFailure(parser, " o/tag tag1 tag2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_TAG_PREFIX));
    }

}
