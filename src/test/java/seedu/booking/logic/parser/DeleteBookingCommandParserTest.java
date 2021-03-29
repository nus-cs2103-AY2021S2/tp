package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.BOOKING_ID_INPUT_BOOKING_1;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.booking.testutil.TypicalBookings.BOOKING1;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.DeleteBookingCommand;

public class DeleteBookingCommandParserTest {

    private DeleteBookingCommandParser parser = new DeleteBookingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteBookingCommand() {
        assertParseSuccess(parser, BOOKING_ID_INPUT_BOOKING_1, new DeleteBookingCommand(BOOKING1.getId()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "nani??",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
    }
}
