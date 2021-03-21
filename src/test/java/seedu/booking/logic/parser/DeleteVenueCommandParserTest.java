package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.booking.testutil.TypicalVenues.VENUE1;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.DeleteVenueCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteVenueCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteVenueCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteVenueCommandParserTest {

    private DeleteVenueCommandParser parser = new DeleteVenueCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteVenueCommand() {
        assertParseSuccess(parser, "v/Venue1", new DeleteVenueCommand(VENUE1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVenueCommand.MESSAGE_USAGE));
    }
}
