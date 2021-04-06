package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptPersonTagsCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.logic.parser.promptparsers.PromptPersonTagsParser;
import seedu.booking.model.Tag;

public class PromptPersonTagsParserTest {
    private final PromptPersonTagsParser parser = new PromptPersonTagsParser();

    @Test
    public void parsePersonTags_validTags_success() throws ParseException {
        Set<Tag> emptyTags = ParserUtil.parseTagsForPromptCommands("");
        Set<Tag> validTags = ParserUtil.parseTagsForPromptCommands("big, cute");

        // no input
        assertParseSuccess(parser, "", new PromptPersonTagsCommand(emptyTags));

        // more than one valid tag
        assertParseSuccess(parser, "big, cute", new PromptPersonTagsCommand(validTags));

        // no tags parsed
        assertParseSuccess(parser, ",", new PromptPersonTagsCommand(emptyTags));
    }

    @Test
    public void parsePersonTags_invalidTags_failure() {
        // one tag with spacing
        assertParseFailure(parser, "big cute, weird", Tag.MESSAGE_CONSTRAINTS);

        // one tag with spacing
        assertParseFailure(parser, "Jon Snow", Tag.MESSAGE_CONSTRAINTS);

        // more than one valid tag
        assertParseFailure(parser, "ice-cream", Tag.MESSAGE_CONSTRAINTS);
    }
}

