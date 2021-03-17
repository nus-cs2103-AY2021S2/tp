package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.module.logic.commands.CommandTestUtil.TAG_DESC_LOW;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.TagCommand;
import seedu.module.model.tag.Tag;

class TagCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private TagCommandParser parser = new TagCommandParser();

    @Test
    void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_PRIORITY_HIGH, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", TagCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TAG_PRIORITY_HIGH, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TAG_PRIORITY_HIGH, MESSAGE_INVALID_FORMAT);

        // empty tag
        assertParseFailure(parser, "1" + PREFIX_TAG, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // any invalid tag will cause failure
        assertParseFailure(parser, "1" + TAG_DESC_LOW + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC + TAG_DESC_LOW, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_fieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String stubTagString = "Stub";
        Set<Tag> stubTags = new HashSet<>();
        stubTags.add(new Tag(stubTagString));
        String userInput = targetIndex.getOneBased() + " " + PREFIX_TAG.getPrefix() + stubTagString;

        TagCommand expectedCommand = new TagCommand(targetIndex);
        expectedCommand.setTags(stubTags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
