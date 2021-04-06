package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptVenueCapacityCommand;
import seedu.booking.logic.parser.promptparsers.PromptVenueCapacityParser;
import seedu.booking.model.venue.Capacity;

public class PromptVenueCapacityParserTest {
    private final PromptVenueCapacityParser parser = new PromptVenueCapacityParser();

    @Test
    public void parseVenueCapacity_validField_success() {
        // normal integer
        assertParseSuccess(parser, "10",
                new PromptVenueCapacityCommand(new Capacity(10)));

        // boundary value of 1
        assertParseSuccess(parser, "1",
                new PromptVenueCapacityCommand(new Capacity(1)));

        // boundary value of 500000
        assertParseSuccess(parser, "500000",
                new PromptVenueCapacityCommand(new Capacity(500000)));

        // no input
        assertParseSuccess(parser, "",
                new PromptVenueCapacityCommand(new Capacity(PromptVenueCapacityParser.DEFAULT_CAPACITY)));
    }
}
