package seedu.timeforwheels.logic.parser;

import static seedu.timeforwheels.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.TAG_DESC_FRAGILE;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.TAG_DESC_HEAVY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_TAG_FRAGILE;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_TAG_HEAVY;
import static seedu.timeforwheels.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.timeforwheels.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.timeforwheels.testutil.TypicalCustomers.AMY;
import static seedu.timeforwheels.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.logic.commands.AddCommand;
import seedu.timeforwheels.model.customer.Address;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.Email;
import seedu.timeforwheels.model.customer.Name;
import seedu.timeforwheels.model.customer.Phone;
import seedu.timeforwheels.model.tag.Tag;
import seedu.timeforwheels.testutil.CustomerBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Customer expectedCustomer = new CustomerBuilder(BOB).withTags(VALID_TAG_FRAGILE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRAGILE + DATE_DESC_AMY, new AddCommand(expectedCustomer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRAGILE + DATE_DESC_AMY, new AddCommand(expectedCustomer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRAGILE + DATE_DESC_AMY, new AddCommand(expectedCustomer));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRAGILE + DATE_DESC_AMY, new AddCommand(expectedCustomer));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRAGILE + DATE_DESC_AMY, new AddCommand(expectedCustomer));

        // multiple tags - all accepted
        Customer expectedCustomerMultipleTags = new CustomerBuilder(BOB).withTags(VALID_TAG_FRAGILE, VALID_TAG_HEAVY)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HEAVY + TAG_DESC_FRAGILE + DATE_DESC_AMY, new AddCommand(expectedCustomerMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Customer expectedCustomer = new CustomerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + DATE_DESC_AMY, new AddCommand(expectedCustomer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HEAVY + TAG_DESC_FRAGILE + DATE_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HEAVY + TAG_DESC_FRAGILE + DATE_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HEAVY + TAG_DESC_FRAGILE + DATE_DESC_AMY, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HEAVY + TAG_DESC_FRAGILE + DATE_DESC_AMY, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRAGILE + DATE_DESC_AMY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + DATE_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HEAVY + TAG_DESC_FRAGILE + DATE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
