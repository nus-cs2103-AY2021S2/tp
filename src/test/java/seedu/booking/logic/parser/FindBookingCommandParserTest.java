package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DESC;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.FindBookingCommand;
import seedu.booking.model.booking.BookingIdContainsKeywordsPredicate;

public class FindBookingCommandParserTest {

    private FindBookingCommandParser parser = new FindBookingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindBookingCommand expectedFindBookingCommand =
                new FindBookingCommand(new BookingIdContainsKeywordsPredicate("1"));
        assertParseSuccess(parser, VALID_BOOKING_ID_DESC, expectedFindBookingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_BOOKING_ID + "\n 1 \n ", expectedFindBookingCommand);
    }

}
