package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_EXPIRYDATE_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.storemando.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.storemando.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.storemando.logic.commands.CommandTestUtil.QUANTITY_DESC_AMY;
import static seedu.storemando.logic.commands.CommandTestUtil.QUANTITY_DESC_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.storemando.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_EXPIRYDATE_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_QUANTITY_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.storemando.testutil.TypicalItems.AMY;
import static seedu.storemando.testutil.TypicalItems.BOB;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.AddCommand;
import seedu.storemando.model.item.ExpiryDate;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.Location;
import seedu.storemando.model.item.Quantity;
import seedu.storemando.model.tag.Tag;
import seedu.storemando.testutil.ItemBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + QUANTITY_DESC_BOB
            + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, NAME_DESC_BOB + QUANTITY_DESC_AMY + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple emails - last expirydate accepted
        assertParseSuccess(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_AMY
            + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple addresses - last location accepted
        assertParseSuccess(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_AMY
            + LOCATION_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedItem));

        // multiple tags - all accepted
        Item expectedItemMultipleTags = new ItemBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedItemMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Item expectedItem = new ItemBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + QUANTITY_DESC_AMY + EXPIRYDATE_DESC_AMY + LOCATION_DESC_AMY,
            new AddCommand(expectedItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB,
            expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_QUANTITY_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB,
            expectedMessage);

        // missing expirydate prefix
        assertParseFailure(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + VALID_EXPIRYDATE_BOB + LOCATION_DESC_BOB,
            expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB + VALID_LOCATION_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_QUANTITY_BOB + VALID_EXPIRYDATE_BOB
            + VALID_LOCATION_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ItemName.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_QUANTITY_DESC + EXPIRYDATE_DESC_BOB
            + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Quantity.MESSAGE_CONSTRAINTS);

        // invalid expirydate
        assertParseFailure(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + INVALID_EXPIRYDATE_DESC
            + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ExpiryDate.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB
            + INVALID_LOCATION_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB + LOCATION_DESC_BOB
            + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB
            + INVALID_LOCATION_DESC, ItemName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + QUANTITY_DESC_BOB + EXPIRYDATE_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
