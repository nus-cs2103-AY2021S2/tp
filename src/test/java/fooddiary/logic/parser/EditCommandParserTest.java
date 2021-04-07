package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.commons.core.Messages.MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS;
import static fooddiary.logic.parser.CliSyntax.PREFIX_TAG_CATEGORY;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_MILLIONTH_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.commands.EditCommand.EditEntryDescriptor;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.TagCategory;
import fooddiary.testutil.EditEntryDescriptorBuilder;


public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG_CATEGORY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_A, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_A,
                MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS);

        // zero index
        assertParseFailure(parser, "0" + CommandTestUtil.NAME_DESC_A,
                MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS);

        // too high index (above maximum limit for number of entries)
        assertParseFailure(parser, "1000001" + CommandTestUtil.NAME_DESC_A,
                MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS); // invalid price
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_REVIEW_DESC, Review.MESSAGE_CONSTRAINTS); // invalid review
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_TAG_CATEGORY_DESC, TagCategory.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid rating followed by valid email
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_RATING_DESC
                + CommandTestUtil.REVIEW_DESC_A, Rating.MESSAGE_CONSTRAINTS);

        // valid rating followed by invalid rating. The test case for invalid rating followed by valid rating
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CommandTestUtil.RATING_DESC_B
                + CommandTestUtil.INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Entry} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.TAG_DESC_WESTERN + TAG_EMPTY, TagCategory.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_FASTFOOD
                + TAG_EMPTY + CommandTestUtil.TAG_DESC_WESTERN, TagCategory.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.TAG_DESC_WESTERN, TagCategory.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_REVIEW_DESC
                        + CommandTestUtil.VALID_ADDRESS_A + CommandTestUtil.VALID_RATING_A,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.TAG_DESC_WESTERN
                + CommandTestUtil.REVIEW_DESC_A + CommandTestUtil.ADDRESS_DESC_A
                + CommandTestUtil.NAME_DESC_A + CommandTestUtil.TAG_DESC_FASTFOOD;

        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_A)
                .withRating(CommandTestUtil.VALID_RATING_B).withReviews(CommandTestUtil.VALID_REVIEW_A)
                .withAddress(CommandTestUtil.VALID_ADDRESS_A)
                .withTagCategories(CommandTestUtil.VALID_TAG_CATEGORY_WESTERN,
                                    CommandTestUtil.VALID_TAG_CATEGORY_FASTFOOD).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);


        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased()
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.TAG_DESC_WESTERN
                + CommandTestUtil.REVIEW_DESC_A + CommandTestUtil.ADDRESS_DESC_A
                + CommandTestUtil.NAME_DESC_A + CommandTestUtil.TAG_DESC_FASTFOOD;

        expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.REVIEW_DESC_A;

        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_B)
                .withReviews(CommandTestUtil.VALID_REVIEW_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);


        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased()
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.REVIEW_DESC_A;

        expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_A;
        EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_A;
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        targetIndex = INDEX_THIRD_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.RATING_DESC_A;
        descriptor = new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.RATING_DESC_A;
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price
        targetIndex = INDEX_THIRD_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.PRICE_DESC_A;
        descriptor = new EditEntryDescriptorBuilder().withPrice(CommandTestUtil.VALID_PRICE_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.PRICE_DESC_A;
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // review
        targetIndex = INDEX_THIRD_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.REVIEW_DESC_A;
        descriptor = new EditEntryDescriptorBuilder().withReviews(CommandTestUtil.VALID_REVIEW_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.REVIEW_DESC_A;
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        targetIndex = INDEX_THIRD_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.ADDRESS_DESC_A;
        descriptor = new EditEntryDescriptorBuilder().withAddress(CommandTestUtil.VALID_ADDRESS_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.ADDRESS_DESC_A;
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        targetIndex = INDEX_THIRD_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_FASTFOOD;
        descriptor = new EditEntryDescriptorBuilder()
                        .withTagCategories(CommandTestUtil.VALID_TAG_CATEGORY_FASTFOOD).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_FASTFOOD;
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.RATING_DESC_A + CommandTestUtil.PRICE_DESC_A
                + CommandTestUtil.ADDRESS_DESC_A + CommandTestUtil.REVIEW_DESC_A
                + CommandTestUtil.TAG_DESC_FASTFOOD + CommandTestUtil.RATING_DESC_A + CommandTestUtil.PRICE_DESC_A
                + CommandTestUtil.ADDRESS_DESC_A + CommandTestUtil.REVIEW_DESC_A + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.TAG_DESC_WESTERN;

        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_B)
                .withPrice(CommandTestUtil.VALID_PRICE_B)
                .withReviews(CommandTestUtil.VALID_REVIEW_A,
                        CommandTestUtil.VALID_REVIEW_A, CommandTestUtil.VALID_REVIEW_B)
                        .withAddress(CommandTestUtil.VALID_ADDRESS_B)
                .withTagCategories(CommandTestUtil.VALID_TAG_CATEGORY_FASTFOOD,
                        CommandTestUtil.VALID_TAG_CATEGORY_WESTERN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);


        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.RATING_DESC_A + CommandTestUtil.PRICE_DESC_A
                + CommandTestUtil.ADDRESS_DESC_A + CommandTestUtil.REVIEW_DESC_A
                + CommandTestUtil.TAG_DESC_FASTFOOD + CommandTestUtil.RATING_DESC_A + CommandTestUtil.PRICE_DESC_A
                + CommandTestUtil.ADDRESS_DESC_A + CommandTestUtil.REVIEW_DESC_A + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.RATING_DESC_B + CommandTestUtil.PRICE_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.REVIEW_DESC_B + CommandTestUtil.TAG_DESC_WESTERN;

        expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.INVALID_RATING_DESC + CommandTestUtil.RATING_DESC_B;
        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased()
                + CommandTestUtil.INVALID_RATING_DESC + CommandTestUtil.RATING_DESC_B;
        descriptor = new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_B).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        targetIndex = INDEX_FIRST_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.INVALID_RATING_DESC + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.RATING_DESC_B;
        descriptor = new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_B)
                .withReviews(CommandTestUtil.VALID_REVIEW_B)
                .withAddress(CommandTestUtil.VALID_ADDRESS_B).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + CommandTestUtil.REVIEW_DESC_B
                + CommandTestUtil.INVALID_RATING_DESC + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.RATING_DESC_B;
        descriptor = new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_B)
                .withReviews(CommandTestUtil.VALID_REVIEW_B)
                .withAddress(CommandTestUtil.VALID_ADDRESS_B).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withTagCategories().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);


        targetIndex = INDEX_MILLIONTH_ENTRY;
        userInput = targetIndex.getOneBased() + TAG_EMPTY;

        descriptor = new EditEntryDescriptorBuilder().withTagCategories().build();
        expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
