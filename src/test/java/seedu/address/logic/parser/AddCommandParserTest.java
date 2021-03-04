package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPIRYDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.AMY;
import static seedu.address.testutil.TypicalItems.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.item.ExpiryDate;
import seedu.address.model.item.Item;
import seedu.address.model.item.Location;
import seedu.address.model.item.Name;
import seedu.address.model.item.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ItemBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_AMY + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_AMY
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple tags - all accepted
        Item expectedItemMultipleTags = new ItemBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedItemMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Item expectedItem = new ItemBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EXPIRYDATE_DESC_AMY + LOCATION_DESC_AMY,
            new AddCommand(expectedItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EXPIRYDATE_BOB + LOCATION_DESC_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + VALID_LOCATION_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EXPIRYDATE_BOB + VALID_LOCATION_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EXPIRYDATE_DESC + LOCATION_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ExpiryDate.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + INVALID_LOCATION_DESC
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB
            + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB + INVALID_LOCATION_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EXPIRYDATE_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
