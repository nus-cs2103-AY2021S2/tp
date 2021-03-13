package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REVIEW_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.REVIEW_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REVIEW_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_WESTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REVIEW_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WESTERN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Review;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FASTFOOD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FASTFOOD, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FASTFOOD, new AddCommand(expectedPerson));

        // multiple rating - last rating accepted
        assertParseSuccess(parser, NAME_DESC_BOB + RATING_DESC_AMY + RATING_DESC_BOB + REVIEW_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FASTFOOD, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_AMY + REVIEW_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FASTFOOD, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FASTFOOD, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FASTFOOD, VALID_TAG_WESTERN)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_WESTERN + TAG_DESC_FASTFOOD, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + RATING_DESC_AMY + REVIEW_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing rating prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_RATING_BOB + REVIEW_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing REVIEW prefix
        assertParseFailure(parser, NAME_DESC_BOB + RATING_DESC_BOB + VALID_REVIEW_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_RATING_BOB + VALID_REVIEW_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + RATING_DESC_BOB + REVIEW_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_WESTERN + TAG_DESC_FASTFOOD, Name.MESSAGE_CONSTRAINTS);

        // invalid rating
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_RATING_DESC + REVIEW_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_WESTERN + TAG_DESC_FASTFOOD, Rating.MESSAGE_CONSTRAINTS);

        // invalid REVIEW
        assertParseFailure(parser, NAME_DESC_BOB + RATING_DESC_BOB + INVALID_REVIEW_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_WESTERN + TAG_DESC_FASTFOOD, Review.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_WESTERN + TAG_DESC_FASTFOOD, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + TAG_DESC_FASTFOOD, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + RATING_DESC_BOB + REVIEW_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + RATING_DESC_BOB + REVIEW_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_WESTERN + TAG_DESC_FASTFOOD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
