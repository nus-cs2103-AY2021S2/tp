package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_CAPACITY_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_HALL;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.booking.testutil.TypicalVenues.HALL;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.CreateVenueCommand;
import seedu.booking.model.venue.Venue;
import seedu.booking.testutil.VenueBuilder;

public class CreateVenueCommandParserTest {
    private CreateVenueCommandParser parser = new CreateVenueCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Venue expectedVenue = new VenueBuilder(HALL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VENUE_NAME_DESC_HALL + VENUE_CAPACITY_DESC_HALL,
                new CreateVenueCommand(expectedVenue));

        // multiple names - last name accepted
        assertParseSuccess(parser, VENUE_NAME_DESC_FIELD + VENUE_NAME_DESC_HALL
                + VENUE_CAPACITY_DESC_HALL,
                new CreateVenueCommand(expectedVenue));
    }

    /*
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }
    */

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateVenueCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VENUE_CAPACITY_DESC_HALL,
                expectedMessage);

        // missing capacity prefix
        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + VALID_VENUE_CAPACITY_HALL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_VENUE_NAME_HALL + VALID_VENUE_CAPACITY_HALL,
                expectedMessage);
    }

    /*
    // need to add MESSAGE_CONSTRAINTS
    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT);

        // invalid capacity
        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + INVALID_VENUE_CAPACITY_DESC, expectedMessage);
        assertParseFailure(parser, VENUE_CAPACITY_DESC_HALL + INVALID_VENUE_CAPACITY_DESC2, expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VENUE_NAME_DESC_HALL + VENUE_CAPACITY_DESC_HALL,
                expectedMessage);
    }
     */
}
