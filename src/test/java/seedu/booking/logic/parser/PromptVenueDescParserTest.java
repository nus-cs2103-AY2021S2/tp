package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptVenueDescCommand;
import seedu.booking.logic.parser.promptparsers.PromptVenueDescParser;

public class PromptVenueDescParserTest {
    private final PromptVenueDescParser parser = new PromptVenueDescParser();

    @Test
    public void parseVenueDesc_validField_success() {
        // random string
        assertParseSuccess(parser, "any random description/_string@",
                new PromptVenueDescCommand("any random description/_string@"));

        // no input
        assertParseSuccess(parser, "",
                new PromptVenueDescCommand(PromptVenueDescParser.DEFAULT_VENUE_DESCRIPTION));
    }
}
