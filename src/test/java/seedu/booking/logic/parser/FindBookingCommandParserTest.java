package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.FindBookingCommand;
import seedu.booking.model.booking.BookingIdContainsKeywordsPredicate;

public class FindBookingCommandParserTest {

    private FindBookingCommandParser parser = new FindBookingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "b/   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindBookingCommand expectedFindBookingCommand =
                new FindBookingCommand(new BookingIdContainsKeywordsPredicate("1"));
        assertParseSuccess(parser, "find_booking bid/1", expectedFindBookingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "find_booking bid/ \n 1 \n ", expectedFindBookingCommand);
    }

}
