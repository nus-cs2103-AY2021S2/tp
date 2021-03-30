package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.InsurancePolicyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    //@@author wongkokian
    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedNameFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        FindCommand expectedPhoneFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("12345", "98765")));
        FindCommand expectedEmailFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("sam@mail.com", "bob@mail.com")));
        FindCommand expectedAddressFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Bedok", "Tampines")));
        FindCommand expectedTagFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friend", "family")));
        FindCommand expectedInsurancePolicyFindCommand =
                new FindCommand(new InsurancePolicyContainsKeywordsPredicate(Arrays.asList("P12345", "P98765")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "n/Alice & Bob", expectedNameFindCommand);
        assertParseSuccess(parser, "p/12345 & 98765", expectedPhoneFindCommand);
        assertParseSuccess(parser, "e/sam@mail.com & bob@mail.com", expectedEmailFindCommand);
        assertParseSuccess(parser, "a/Bedok & Tampines", expectedAddressFindCommand);
        assertParseSuccess(parser, "t/friend & family", expectedTagFindCommand);
        assertParseSuccess(parser, "i/P12345 & P98765", expectedInsurancePolicyFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n n/ \n Alice \n & \t Bob  \t", expectedNameFindCommand);
        assertParseSuccess(parser, "\n p/ \n 12345 \n & \t 98765  \t", expectedPhoneFindCommand);
        assertParseSuccess(parser, "\n e/ \n sam@mail.com \n & \t bob@mail.com  \t", expectedEmailFindCommand);
        assertParseSuccess(parser, "\n a/ \n Bedok \n & \t Tampines  \t", expectedAddressFindCommand);
        assertParseSuccess(parser, "\n t/ \n friend \n & \t family  \t", expectedTagFindCommand);
        assertParseSuccess(parser, "\n i/ \n P12345 \n & \t P98765  \t", expectedInsurancePolicyFindCommand);
    }

    @Test
    public void parse_invalidFlag_throwsParseException() {
        assertParseFailure(parser, "address/Bedok & Tampines",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeywords_throwParseException() {
        assertParseFailure(parser, "n/John & & Tom",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleFlag_throwParseException() {
        assertParseFailure(parser, "n/John p/98765432",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noFlag_throwParseException() {
        assertParseFailure(parser, "98765432",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
