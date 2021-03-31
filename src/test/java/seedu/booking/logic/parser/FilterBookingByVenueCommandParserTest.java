package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;
import seedu.booking.logic.commands.FilterBookingByVenueCommand;

class FilterBookingByVenueCommandParserTest {

    private FilterBookingByVenueCommandParser parser = new FilterBookingByVenueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterBookingByVenueCommand.MESSAGE_USAGE));
    }
}