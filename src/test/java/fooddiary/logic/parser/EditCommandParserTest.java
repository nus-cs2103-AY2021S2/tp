package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CliSyntax.PREFIX_TAG;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.logic.commands.EditCommand;
import fooddiary.logic.commands.EditCommand.EditEntryDescriptor;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;
import fooddiary.testutil.EditEntryDescriptorBuilder;


public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

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
                + CommandTestUtil.INVALID_REVIEW_DESC, Review.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid rating followed by valid email
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_RATING_DESC
                + CommandTestUtil.REVIEW_DESC_AMY, Rating.MESSAGE_CONSTRAINTS);

        // valid rating followed by invalid rating. The test case for invalid rating followed by valid rating
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CommandTestUtil.RATING_DESC_BOB
                + CommandTestUtil.INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Entry} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.TAG_DESC_WESTERN + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_FASTFOOD
                + TAG_EMPTY + CommandTestUtil.TAG_DESC_WESTERN, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.TAG_DESC_WESTERN, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_REVIEW_DESC
                        + CommandTestUtil.VALID_ADDRESS_AMY + CommandTestUtil.VALID_RATING_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.TAG_DESC_WESTERN
                + CommandTestUtil.REVIEW_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FASTFOOD;

        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
                .withRating(CommandTestUtil.VALID_RATING_BOB).withReview(CommandTestUtil.VALID_REVIEW_AMY)
                .withAddress(CommandTestUtil.VALID_ADDRESS_AMY)
                .withTags(CommandTestUtil.VALID_TAG_WESTERN, CommandTestUtil.VALID_TAG_FASTFOOD).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.REVIEW_DESC_AMY;

        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_BOB)
                .withReview(CommandTestUtil.VALID_REVIEW_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_AMY;
        EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = targetIndex.getOneBased() + CommandTestUtil.RATING_DESC_AMY;
        descriptor = new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // review
        userInput = targetIndex.getOneBased() + CommandTestUtil.REVIEW_DESC_AMY;
        descriptor = new EditEntryDescriptorBuilder().withReview(CommandTestUtil.VALID_REVIEW_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + CommandTestUtil.ADDRESS_DESC_AMY;
        descriptor = new EditEntryDescriptorBuilder().withAddress(CommandTestUtil.VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_FASTFOOD;
        descriptor = new EditEntryDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_FASTFOOD).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.RATING_DESC_AMY
                + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.REVIEW_DESC_AMY
                + CommandTestUtil.TAG_DESC_FASTFOOD + CommandTestUtil.RATING_DESC_AMY
                + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.REVIEW_DESC_AMY + CommandTestUtil.TAG_DESC_FASTFOOD
                + CommandTestUtil.RATING_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.REVIEW_DESC_BOB + CommandTestUtil.TAG_DESC_WESTERN;

        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_BOB)
                .withReview(CommandTestUtil.VALID_REVIEW_BOB).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(CommandTestUtil.VALID_TAG_FASTFOOD, CommandTestUtil.VALID_TAG_WESTERN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.INVALID_RATING_DESC + CommandTestUtil.RATING_DESC_BOB;
        EditCommand.EditEntryDescriptor descriptor =
                new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + CommandTestUtil.REVIEW_DESC_BOB
                + CommandTestUtil.INVALID_RATING_DESC + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.RATING_DESC_BOB;
        descriptor = new EditEntryDescriptorBuilder().withRating(CommandTestUtil.VALID_RATING_BOB)
                .withReview(CommandTestUtil.VALID_REVIEW_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
