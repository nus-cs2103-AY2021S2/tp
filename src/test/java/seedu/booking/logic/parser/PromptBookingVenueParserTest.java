package seedu.booking.logic.parser;

import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptBookingVenueCommand;
import seedu.booking.logic.parser.promptparsers.PromptBookingVenueParser;
import seedu.booking.model.venue.VenueName;

public class PromptBookingVenueParserTest {
    private final PromptBookingVenueParser parser = new PromptBookingVenueParser();

    @Test
    public void parseVenueName_validField_success() {
        // Venue name with combination of alphanumeric characters
        assertParseSuccess(parser, " eorij019248sdo",
                new PromptBookingVenueCommand(new VenueName("eorij019248sdo")));

        // Venue name with spaces
        assertParseSuccess(parser, VALID_VENUE_NAME_HALL,
                new PromptBookingVenueCommand(new VenueName(VALID_VENUE_NAME_HALL)));

        // Venue name with hyphens
        assertParseSuccess(parser, "Fish-and-chips shop",
                new PromptBookingVenueCommand(new VenueName("Fish-and-chips shop")));

        // Venue name with apostrophes
        assertParseSuccess(parser, "Jo's Ice Cream",
                new PromptBookingVenueCommand(new VenueName("Jo's Ice Cream")));
    }

    @Test
    public void parseVenueName_invalidField_failure() {
        String expectedMessage = VenueName.MESSAGE_CONSTRAINTS;
        // No input
        assertParseFailure(parser, "", expectedMessage);

        // Invalid characters
        assertParseFailure(parser, " b/£tjohn", expectedMessage);
    }
}
