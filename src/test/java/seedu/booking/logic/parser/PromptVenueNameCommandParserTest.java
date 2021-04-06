package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_APOSTROPHE;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HYPHEN;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_APOSTROPHE;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VENUE_NAME_DESC_HYPHEN;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptAddVenueCommand;
import seedu.booking.logic.parser.promptparsers.PromptVenueNameCommandParser;
import seedu.booking.model.venue.VenueName;

public class PromptVenueNameCommandParserTest {
    private final PromptVenueNameCommandParser parser = new PromptVenueNameCommandParser();

    @Test
    public void parseVenueName_validField_success() {
        // Venue name with combination of alphanumeric characters
        assertParseSuccess(parser, " v/eorij019248sdo",
                new PromptAddVenueCommand(new VenueName("eorij019248sdo")));

        // Venue name with spaces
        assertParseSuccess(parser, VENUE_NAME_DESC_HALL,
                new PromptAddVenueCommand(new VenueName(VALID_VENUE_NAME_HALL)));

        // Venue name with hyphens
        assertParseSuccess(parser, VENUE_NAME_DESC_HYPHEN,
                new PromptAddVenueCommand(new VenueName(VALID_VENUE_NAME_HYPHEN)));

        // Venue name with apostrophes
        assertParseSuccess(parser, VENUE_NAME_DESC_APOSTROPHE,
                new PromptAddVenueCommand(new VenueName(VALID_VENUE_NAME_APOSTROPHE)));
    }

    @Test
    public void parseVenueName_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        PromptAddVenueCommand.MESSAGE_USAGE);
        // No input
        assertParseFailure(parser, "", expectedMessage);

        // No spacing before prefix
        assertParseFailure(parser, "v/Jojo", expectedMessage);

        // No prefix
        assertParseFailure(parser, "Victoria", expectedMessage);
    }
}
