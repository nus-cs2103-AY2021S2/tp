package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fooddiary.testutil.TypicalEntries.AMY;
import static fooddiary.testutil.TypicalEntries.BOB;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.AddCommand;
import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;
import fooddiary.testutil.EntryBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Entry expectedEntry = new EntryBuilder(BOB).withTags(CommandTestUtil.VALID_TAG_FASTFOOD).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.REVIEW_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.REVIEW_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple rating - last rating accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_AMY
                + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.REVIEW_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_AMY + CommandTestUtil.REVIEW_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FASTFOOD, new AddCommand(expectedEntry));

        // multiple tags - all accepted
        Entry expectedEntryMultipleTags =
                new EntryBuilder(BOB).withTags(CommandTestUtil.VALID_TAG_FASTFOOD, CommandTestUtil.VALID_TAG_WESTERN)
                .build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD,
                new AddCommand(expectedEntryMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Entry expectedEntry = new EntryBuilder(AMY).withTags().build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.RATING_DESC_AMY
                        + CommandTestUtil.REVIEW_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY,
                new AddCommand(expectedEntry));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.RATING_DESC_BOB
                        + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing rating prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.VALID_RATING_BOB
                        + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing REVIEW prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                        + CommandTestUtil.VALID_REVIEW_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                        + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.VALID_RATING_BOB
                        + CommandTestUtil.VALID_REVIEW_BOB + CommandTestUtil.VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Name.MESSAGE_CONSTRAINTS);

        // invalid rating
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.INVALID_RATING_DESC
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Rating.MESSAGE_CONSTRAINTS);

        // invalid REVIEW
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.INVALID_REVIEW_DESC + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Review.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC
                + CommandTestUtil.TAG_DESC_WESTERN + CommandTestUtil.TAG_DESC_FASTFOOD, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.TAG_DESC_FASTFOOD, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.RATING_DESC_BOB
                        + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.REVIEW_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_WESTERN
                        + CommandTestUtil.TAG_DESC_FASTFOOD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
