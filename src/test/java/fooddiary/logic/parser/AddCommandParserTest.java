package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fooddiary.testutil.TypicalEntries.VALID_ENTRY_A;
import static fooddiary.testutil.TypicalEntries.VALID_ENTRY_B;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;
import fooddiary.testutil.EntryBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Entry expectedEntry = new EntryBuilder(VALID_ENTRY_B).withTags(CommandTestUtil.VALID_TAG_FASTFOOD).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_A + CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple rating - last rating accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_A
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple price - last price accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_A + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple reviews - last email accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_A + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_A
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple tags - all accepted
        Entry expectedEntryMultipleTags =
                new EntryBuilder(VALID_ENTRY_B).withTags(CommandTestUtil.VALID_TAG_FASTFOOD,
                CommandTestUtil.VALID_TAG_WESTERN).build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD,
                new AddCommand(expectedEntryMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Entry expectedEntry = new EntryBuilder(VALID_ENTRY_A).withTags().build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_A + CommandTestUtil.RATING_DESC_A
                + CommandTestUtil.PRICE_DESC_A + CommandTestUtil.REVIEW_DESC_A
                + CommandTestUtil.ADDRESS_DESC_A, new AddCommand(expectedEntry));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B,
                expectedMessage);

        // missing rating prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.VALID_RATING_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B,
                expectedMessage);

        // missing price prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.VALID_PRICE_B + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B,
                expectedMessage);

        // missing review prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.VALID_REVIEW_B + CommandTestUtil.ADDRESS_DESC_B,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.VALID_ADDRESS_B,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_B + CommandTestUtil.VALID_RATING_B
                + CommandTestUtil.VALID_PRICE_B + CommandTestUtil.VALID_REVIEW_B
                + CommandTestUtil.VALID_ADDRESS_B,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Name.MESSAGE_CONSTRAINTS);

        // invalid rating
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.INVALID_RATING_DESC
                + CommandTestUtil.PRICE_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Rating.MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.INVALID_PRICE_DESC
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Price.MESSAGE_CONSTRAINTS);

        // invalid REVIEW
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B
                + CommandTestUtil.INVALID_REVIEW_DESC + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Review.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.INVALID_ADDRESS_DESC
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.TAG_DESC_FASTFOOD, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.PRICE_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_WESTERN
                + CommandTestUtil.TAG_DESC_FASTFOOD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
