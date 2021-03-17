package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.FindVenueCommand;
import seedu.booking.model.booking.VenueNameContainsKeywordsPredicate;

public class FindVenueCommandParserTest {

    private FindVenueCommandParser parser = new FindVenueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindVenueCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindVenueCommand expectedFindCommand =
                new FindVenueCommand(new VenueNameContainsKeywordsPredicate(Arrays.asList("Hall")));
        assertParseSuccess(parser, "find_venue v/Hall", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n find_venue v/ \n Hall  \t", expectedFindCommand);
    }

}
