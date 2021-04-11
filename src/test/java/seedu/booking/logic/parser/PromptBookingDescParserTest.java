package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptBookingDescCommand;
import seedu.booking.logic.parser.promptparsers.PromptBookingDescParser;
import seedu.booking.model.booking.Description;

public class PromptBookingDescParserTest {
    private final PromptBookingDescParser parser = new PromptBookingDescParser();

    @Test
    public void parseBookingDesc_validField_success() {
        // random string
        assertParseSuccess(parser, "any random description/_string@",
                new PromptBookingDescCommand(new Description("any random description/_string@")));

        // no input
        assertParseSuccess(parser, "",
                new PromptBookingDescCommand(new Description("No description provided.")));
    }
}
