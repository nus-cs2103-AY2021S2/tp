package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptBookingTagsCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.logic.parser.promptparsers.PromptBookingTagsParser;
import seedu.booking.model.Tag;

public class PromptBookingTagsParserTest {

    private final PromptBookingTagsParser parser = new PromptBookingTagsParser();

    @Test
    public void parseBookingTags_validTags_success() throws ParseException {
        Set<Tag> emptyTags = ParserUtil.parseTagsForPromptCommands("");
        Set<Tag> validTags = ParserUtil.parseTagsForPromptCommands("important, booking");

        // no input
        assertParseSuccess(parser, "", new PromptBookingTagsCommand(emptyTags));

        // more than one valid tag
        assertParseSuccess(parser, "important, booking", new PromptBookingTagsCommand(validTags));
    }

    @Test
    public void parseBookingTags_invalidTags_failure() throws ParseException {
        // one tag with spacing
        assertParseFailure(parser, "big cute, weird", Tag.MESSAGE_CONSTRAINTS);

        // more than one valid tag
        assertParseFailure(parser, "ice-cream", Tag.MESSAGE_CONSTRAINTS);
    }

}
