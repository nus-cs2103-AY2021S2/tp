package seedu.booking.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.INVALID_VENUE_CAPACITY_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.INVALID_VENUE_CAPACITY_DESC2;
import static seedu.booking.logic.commands.CommandTestUtil.INVALID_VENUE_NAME_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.booking.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_DESC;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_DESCRIPTION_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_DESCRIPTION_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_TAGS_DESC_HALL;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.booking.testutil.TypicalVenues.HALL;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.AddVenueCommand;
import seedu.booking.logic.commands.PromptAddVenueCommand;
import seedu.booking.logic.parser.promptparsers.PromptAddVenueCommandParser;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;
import seedu.booking.testutil.VenueBuilder;

public class AddVenueCommandParserTest {
    private final AddVenueCommandParser parser = new AddVenueCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Venue expectedVenue = new VenueBuilder(HALL).build();

        // whitespace only preamble
        assertParseSuccess(parser,
            PREAMBLE_WHITESPACE + VENUE_NAME_DESC_HALL
                        + VENUE_CAPACITY_DESC_HALL + VENUE_DESCRIPTION_DESC_HALL + VENUE_TAGS_DESC_HALL,
                      new AddVenueCommand(expectedVenue));

        // multiple names - last name accepted
        assertParseSuccess(parser, VENUE_NAME_DESC_FIELD + VENUE_NAME_DESC_HALL
                       + VENUE_CAPACITY_DESC_HALL + VENUE_DESCRIPTION_DESC_HALL + VENUE_TAGS_DESC_HALL,
                       new AddVenueCommand(expectedVenue));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PromptAddVenueCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VENUE_CAPACITY_DESC_HALL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VALID_VENUE_CAPACITY_HALL
                + VALID_VENUE_DESCRIPTION_HALL,
                expectedMessage);
    }


    @Test
    public void parse_optionalFieldMissing_success() {
        Venue expectedVenueWithoutCapacity = new VenueBuilder(HALL)
                .withCapacity(VenueBuilder.DEFAULT_CAPACITY).build();
        Venue expectedVenueWithoutDescription = new VenueBuilder(HALL)
                .withDescription(VenueBuilder.DEFAULT_DESCRIPTION).build();

        // missing capacity prefix
        assertParseSuccess(parser, VENUE_NAME_DESC_HALL
                + VENUE_DESCRIPTION_DESC_HALL + VENUE_TAGS_DESC_HALL,
                new AddVenueCommand(expectedVenueWithoutCapacity));

        // check default capacity is set to 10 if no capacity is parsed
        assertEquals(expectedVenueWithoutCapacity.getCapacity().toString(),
                String.valueOf(PromptAddVenueCommandParser.DEFAULT_CAPACITY));

        // missing description prefix
        assertParseSuccess(parser, VENUE_NAME_DESC_HALL
                + VENUE_CAPACITY_DESC_HALL + VENUE_TAGS_DESC_HALL,
                new AddVenueCommand(expectedVenueWithoutDescription));

        // all optional fields missing success
        Venue expectedVenue = new VenueBuilder().withName(HALL.getVenueName().toString()).build();
        assertParseSuccess(parser, VENUE_NAME_DESC_HALL,
                new AddVenueCommand(expectedVenue));

    }


    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PromptAddVenueCommand.MESSAGE_USAGE);
        String expectedMessageForInvalidName = VenueName.MESSAGE_CONSTRAINTS;

        // invalid capacity
        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + INVALID_VENUE_CAPACITY_DESC, expectedMessage);
        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + INVALID_VENUE_CAPACITY_DESC2, expectedMessage);

        // invalid venue name
        assertParseFailure(parser, INVALID_VENUE_NAME_DESC + VALID_VENUE_CAPACITY_DESC,
                expectedMessageForInvalidName);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VENUE_NAME_DESC_HALL + VENUE_CAPACITY_DESC_HALL,
                expectedMessage);
    }
}
