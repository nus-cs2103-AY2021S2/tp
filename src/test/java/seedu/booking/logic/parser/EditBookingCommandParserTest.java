package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_BOOKER_EMAIL_AMY_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_BOOKER_EMAIL_BOB_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_DESCRIPTION_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_DESCRIPTION_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_END_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_END_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_START_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_START_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_TAGS_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_TAGS_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_VENUE_NAME_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_VENUE_NAME_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.INVALID_BOOKING_EMAIL_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_BOOKER_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_DESCRIPTION_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_END_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_START_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_TAGS_HALL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.booking.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.booking.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.core.index.Index;
import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.testutil.EditBookingCommandDescriptorBuilder;

class EditBookingCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE);

    private EditBookingCommandParser parser = new EditBookingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, BOOKING_BOOKER_EMAIL_AMY_DESC_HALL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditBookingCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL, Messages.INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL, Messages.INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", Messages.INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", Messages.INVALID_INDEX);
    }


    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + BOOKING_VENUE_NAME_DESC_HALL
                + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL + BOOKING_DESCRIPTION_DESC_HALL
                + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL + BOOKING_TAGS_DESC_HALL;

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_BOOKING_BOOKER_EMAIL_AMY)
                .withVenueName(VALID_VENUE_NAME_HALL)
                .withBookingStart(VALID_BOOKING_START_HALL)
                .withBookingEnd(VALID_BOOKING_END_HALL)
                .withDescription(VALID_BOOKING_DESCRIPTION_HALL)
                .withTags(VALID_VENUE_TAGS_HALL).build();

        EditBookingCommand expectedCommand = new EditBookingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + BOOKING_VENUE_NAME_DESC_HALL
                + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                + BOOKING_DESCRIPTION_DESC_HALL;

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_BOOKING_BOOKER_EMAIL_AMY)
                .withVenueName(VALID_VENUE_NAME_HALL)
                .withDescription(VALID_BOOKING_DESCRIPTION_HALL).build();

        EditBookingCommand expectedCommand = new EditBookingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // Email
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL;
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_BOOKING_BOOKER_EMAIL_AMY).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // VenueName
        userInput = targetIndex.getOneBased() + BOOKING_VENUE_NAME_DESC_HALL;
        descriptor = new EditBookingCommandDescriptorBuilder()
                .withVenueName(VALID_VENUE_NAME_HALL).build();
        expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Description
        userInput = targetIndex.getOneBased() + BOOKING_DESCRIPTION_DESC_HALL;
        descriptor = new EditBookingCommandDescriptorBuilder()
                .withDescription(VALID_BOOKING_DESCRIPTION_HALL).build();
        expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Booking Start
        userInput = targetIndex.getOneBased() + BOOKING_START_DESC_HALL;
        descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookingStart(VALID_BOOKING_START_HALL).build();
        expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Booking End
        userInput = targetIndex.getOneBased() + BOOKING_END_DESC_HALL;
        descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookingEnd(VALID_BOOKING_END_HALL).build();
        expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + BOOKING_TAGS_DESC_HALL;
        descriptor = new EditBookingCommandDescriptorBuilder()
                .withTags(VALID_VENUE_TAGS_HALL).build();
        expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + BOOKING_VENUE_NAME_DESC_FIELD
                + BOOKING_BOOKER_EMAIL_BOB_DESC_FIELD + BOOKING_DESCRIPTION_DESC_FIELD
                + BOOKING_START_DESC_FIELD + BOOKING_END_DESC_FIELD + BOOKING_TAGS_DESC_FIELD
                + BOOKING_VENUE_NAME_DESC_FIELD + BOOKING_BOOKER_EMAIL_BOB_DESC_FIELD
                + BOOKING_DESCRIPTION_DESC_FIELD + BOOKING_START_DESC_FIELD
                + BOOKING_END_DESC_FIELD + BOOKING_TAGS_DESC_FIELD
                + BOOKING_VENUE_NAME_DESC_HALL + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL
                + BOOKING_DESCRIPTION_DESC_HALL + BOOKING_START_DESC_HALL
                + BOOKING_END_DESC_HALL + BOOKING_TAGS_DESC_HALL;

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_BOOKING_BOOKER_EMAIL_AMY)
                .withVenueName(VALID_VENUE_NAME_HALL)
                .withBookingStart(VALID_BOOKING_START_HALL)
                .withBookingEnd(VALID_BOOKING_END_HALL)
                .withDescription(VALID_BOOKING_DESCRIPTION_HALL)
                .withTags(VALID_VENUE_TAGS_HALL).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_BOOKING_EMAIL_DESC + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL;
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_BOOKING_BOOKER_EMAIL_AMY).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_BOOKING_EMAIL_DESC + BOOKING_VENUE_NAME_DESC_HALL
                + BOOKING_BOOKER_EMAIL_AMY_DESC_HALL + BOOKING_DESCRIPTION_DESC_HALL
                + BOOKING_START_DESC_HALL + BOOKING_END_DESC_HALL + BOOKING_TAGS_DESC_HALL;
        descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_BOOKING_BOOKER_EMAIL_AMY)
                .withVenueName(VALID_VENUE_NAME_HALL)
                .withBookingStart(VALID_BOOKING_START_HALL)
                .withBookingEnd(VALID_BOOKING_END_HALL)
                .withDescription(VALID_BOOKING_DESCRIPTION_HALL)
                .withTags(VALID_VENUE_TAGS_HALL).build();
        expectedCommand = new EditBookingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditBookingCommand.EditBookingDescriptor descriptor =
                new EditBookingCommandDescriptorBuilder().withTags().build();
        EditBookingCommand expectedCommand = new EditBookingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
