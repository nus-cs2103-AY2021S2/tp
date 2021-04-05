package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE_OF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.ModeOfContact;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ModeOfContactPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonBlacklistedPredicate;
import seedu.address.model.person.predicates.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsNumbersPredicate;
import seedu.address.model.person.predicates.ReturnTruePredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();
    private ReturnTruePredicate returnTruePredicate = new ReturnTruePredicate();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyField_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate,
                        new PersonTagContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_TAG + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate,
                        new AddressContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_ADDRESS + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_ADDRESS + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_EMAIL + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_EMAIL + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, new PhoneContainsNumbersPredicate(Arrays.asList("69", "420")),
                        returnTruePredicate, returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_PHONE + "69 420", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_PHONE + " \n 69 \n \t 420  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidPhoneArgs_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_PHONE + "sixty nine", "Phone field only accepts numbers.");
    }

    @Test
    public void parse_validBlacklistStatus_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate,
                        new PersonBlacklistedPredicate(true), returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_BLACKLIST + "TrUe", expectedFindCommand);
    }

    @Test
    public void parse_multipleBlacklistKeywords_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate,
                        new PersonBlacklistedPredicate(true), returnTruePredicate);
        // blacklist field only takes the first parameter
        assertParseSuccess(parser, " " + PREFIX_BLACKLIST + "true 420", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_BLACKLIST + " \n true \n \t 420  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidBlacklistStatus_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_BLACKLIST + "blaze it",
                "Blacklist field only accepts true or false.");
    }

    @Test
    public void parse_validModeOfContact_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        new ModeOfContactPredicate(new ModeOfContact("email")));
        assertParseSuccess(parser, " " + PREFIX_MODE_OF_CONTACT + "eMaIL", expectedFindCommand);
    }

    @Test
    public void parse_multipleModeOfContactKeywords_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate, returnTruePredicate,
                        new ModeOfContactPredicate(new ModeOfContact("email")));
        // mode of contact field only takes the first parameter.
        assertParseSuccess(parser, " " + PREFIX_MODE_OF_CONTACT + "email sixty nine", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MODE_OF_CONTACT + " \n email \n \t sixty  \t nine",
                expectedFindCommand);
    }

    @Test
    public void parse_invalidModeOfContact_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_MODE_OF_CONTACT + "bruh",
                "Mode of contact field only accepts phone, email or address.");
    }

    @Test
    public void parse_validNameAndTagArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new PersonTagContainsKeywordsPredicate(Arrays.asList("tagOne", "tagTwo")),
                        returnTruePredicate, returnTruePredicate,
                        returnTruePredicate, returnTruePredicate, returnTruePredicate);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob " + PREFIX_TAG + "tagOne tagTwo",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t " + PREFIX_TAG + "tagOne \t tagTwo\n\t",
                expectedFindCommand);
    }
}
