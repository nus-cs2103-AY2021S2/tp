package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_ASKING_PRICE_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_ASKING_PRICE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_CONTACT_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_CONTACT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_NAME_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENT_ASKING_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENT_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENT_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLIENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_DEADLINE_IN_INVALID_FORMAT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_DEADLINE_IN_VALID_FORMAT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_POSTAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROPERTY_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.POSTAL_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.POSTAL_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_4_BEDROOMS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_99_YEAR_LEASEHOLD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BALCONY_AND_99_YEAR_LEASEHOLD;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROPERTY_TAG_99_YEAR_LEASEHOLD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROPERTY_TAG_BALCONY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BURGHLEY_DRIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_MAYFAIR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalProperties.BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;
import seedu.address.testutil.PropertyBuilder;

public class AddPropertyCommandParserTest {

    private AddPropertyCommandParser parser = new AddPropertyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Property expectedProperty = new PropertyBuilder(BURGHLEY_DRIVE)
                .withRemark(VALID_REMARK_BURGHLEY_DRIVE)
                .withClient(BOB)
                .withTags(VALID_PROPERTY_TAG_99_YEAR_LEASEHOLD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE
                + ADDRESS_DESC_BURGHLEY_DRIVE + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_MAYFAIR + NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE
                + ADDRESS_DESC_BURGHLEY_DRIVE + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple types - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_MAYFAIR + TYPE_DESC_BURGHLEY_DRIVE
                + ADDRESS_DESC_BURGHLEY_DRIVE + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_MAYFAIR
                + ADDRESS_DESC_BURGHLEY_DRIVE + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple postal codes - last postal code accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_MAYFAIR + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_MAYFAIR + DEADLINE_DESC_BURGHLEY_DRIVE
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE + REMARK_DESC_MAYFAIR
                + REMARK_DESC_BURGHLEY_DRIVE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple client name - last client name accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE + REMARK_DESC_BURGHLEY_DRIVE
                + CLIENT_NAME_DESC_ALICE + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple client contact - last client contact accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE + REMARK_DESC_BURGHLEY_DRIVE
                + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_ALICE + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple client email - last client email accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE + REMARK_DESC_BURGHLEY_DRIVE
                + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_ALICE + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple client asking price - last client asking price accepted
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE + REMARK_DESC_BURGHLEY_DRIVE
                + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_ALICE + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedProperty));

        // multiple tags - all accepted
        Property expectedPropertyMultipleTags = new PropertyBuilder(BURGHLEY_DRIVE)
                .withRemark(VALID_REMARK_BURGHLEY_DRIVE)
                .withClient(BOB)
                .withTags(VALID_PROPERTY_TAG_BALCONY, VALID_PROPERTY_TAG_99_YEAR_LEASEHOLD)
                .build();
        assertParseSuccess(parser, NAME_DESC_BURGHLEY_DRIVE + TYPE_DESC_BURGHLEY_DRIVE + ADDRESS_DESC_BURGHLEY_DRIVE
                + POSTAL_DESC_BURGHLEY_DRIVE + DEADLINE_DESC_BURGHLEY_DRIVE + REMARK_DESC_BURGHLEY_DRIVE
                + CLIENT_NAME_DESC_BOB + CLIENT_CONTACT_DESC_BOB + CLIENT_EMAIL_DESC_BOB
                + CLIENT_ASKING_PRICE_DESC_BOB + TAG_DESC_BALCONY_AND_99_YEAR_LEASEHOLD,
                new AddPropertyCommand(expectedPropertyMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // No remarks, no client, no tags
        Property expectedProperty = new PropertyBuilder(MAYFAIR).withTags().build();
        assertParseSuccess(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                        + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR, new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing type prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + VALID_TYPE_MAYFAIR + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + VALID_ADDRESS_MAYFAIR + POSTAL_DESC_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing postal prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + VALID_POSTAL_MAYFAIR
                + DEADLINE_DESC_MAYFAIR, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR
                + VALID_DEADLINE_MAYFAIR, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MAYFAIR + VALID_TYPE_MAYFAIR + VALID_ADDRESS_MAYFAIR
                        + VALID_POSTAL_MAYFAIR + VALID_DEADLINE_MAYFAIR, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_PROPERTY_NAME_DESC + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Name.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, NAME_DESC_MAYFAIR + INVALID_PROPERTY_TYPE_DESC + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Type.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + INVALID_PROPERTY_ADDRESS_DESC
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Address.MESSAGE_CONSTRAINTS);

        // invalid postal
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + INVALID_PROPERTY_POSTAL_DESC + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, PostalCode.MESSAGE_CONSTRAINTS);

        // invalid deadline in invalid format
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + INVALID_PROPERTY_DEADLINE_IN_INVALID_FORMAT_DESC + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Deadline.MESSAGE_CONSTRAINTS);

        // invalid deadline in valid format
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + INVALID_PROPERTY_DEADLINE_IN_VALID_FORMAT_DESC + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Deadline.MESSAGE_INVALID_DATE);

        // invalid remark
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + INVALID_PROPERTY_REMARK_DESC
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Remark.MESSAGE_CONSTRAINTS);

        // invalid client name
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + INVALID_CLIENT_NAME_DESC + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Name.MESSAGE_CONSTRAINTS);

        // invalid client contact
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + INVALID_CLIENT_CONTACT_DESC + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Contact.MESSAGE_CONSTRAINTS);

        // invalid client email
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + INVALID_CLIENT_EMAIL_DESC
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Email.MESSAGE_CONSTRAINTS);

        // invalid client asking price
        assertParseFailure(parser, NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR + ADDRESS_DESC_MAYFAIR
                + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + INVALID_CLIENT_ASKING_PRICE_DESC + TAG_DESC_4_BEDROOMS, AskingPrice.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NAME_DESC_MAYFAIR + INVALID_PROPERTY_TYPE_DESC + ADDRESS_DESC_MAYFAIR
                + INVALID_PROPERTY_POSTAL_DESC + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS, Type.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR
                + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR + REMARK_DESC_MAYFAIR
                + CLIENT_NAME_DESC_ALICE + CLIENT_CONTACT_DESC_ALICE + CLIENT_EMAIL_DESC_ALICE
                + CLIENT_ASKING_PRICE_DESC_ALICE + TAG_DESC_4_BEDROOMS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
    }
}
