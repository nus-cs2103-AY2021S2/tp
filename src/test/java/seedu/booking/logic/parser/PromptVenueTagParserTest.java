package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptVenueTagsCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.logic.parser.promptparsers.PromptVenueTagsParser;
import seedu.booking.model.Tag;

public class PromptVenueTagParserTest {
    private final PromptVenueTagsParser parser = new PromptVenueTagsParser();

    @Test
    public void parseVenueTags_validTags_success() throws ParseException {
        Set<Tag> emptyTags = ParserUtil.parseTagsForPromptCommands("");
        Set<Tag> validTags = ParserUtil.parseTagsForPromptCommands("big, cute");

        // no input
        assertParseSuccess(parser, "", new PromptVenueTagsCommand(emptyTags));

        // more than one valid tag
        assertParseSuccess(parser, "big, cute", new PromptVenueTagsCommand(validTags));
    }

    @Test
    public void parseVenueTags_invalidTags_failure() throws ParseException {
        // one tag with spacing
        assertParseFailure(parser, "big cute, weird", Tag.MESSAGE_CONSTRAINTS);

        // more than one valid tag
        assertParseFailure(parser, "ice-cream", Tag.MESSAGE_CONSTRAINTS);
    }
}
