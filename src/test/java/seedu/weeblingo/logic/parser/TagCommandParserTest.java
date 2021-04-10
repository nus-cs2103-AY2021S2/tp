package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_MIN;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_INPUT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_INPUT_MULTIPLE;
import static seedu.weeblingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.weeblingo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.weeblingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.TagCommand;
import seedu.weeblingo.model.tag.Tag;

public class TagCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

    private TagCommandParser parser = new TagCommandParser();

    // only one part missing at any time
    @Test
    public void parse_missingParts_failure() {
        //no index specified
        assertParseFailure(parser, VALID_TAG_EASY, MESSAGE_INVALID_FORMAT);

        //no tag specified
        assertParseFailure(parser, VALID_START_INTEGER_MIN, TagCommand.MESSAGE_NO_TAGS_PROVIDED);

        //no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        //negative index
        assertParseFailure(parser, "-69 t/" + VALID_TAG_EASY, MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);

        //zero index
        assertParseFailure(parser, "0 t/" + VALID_TAG_EASY, MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);

        //invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        //invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        Set<Tag> easyTag = new HashSet<>();
        easyTag.add(new Tag(VALID_TAG_EASY));

        Set<Tag> multipleTags = new HashSet<>();
        multipleTags.add(new Tag(VALID_TAG_DIFFICULT));
        multipleTags.add(new Tag(VALID_TAG_EASY));

        String userInputSingle = VALID_TAG_INPUT;

        String userInputMultiple = VALID_TAG_INPUT_MULTIPLE;

        //only one tag
        assertParseSuccess(parser, userInputSingle, new TagCommand(targetIndex, easyTag));

        //multiple tags
        assertParseSuccess(parser, userInputMultiple, new TagCommand(targetIndex, multipleTags));
    }
}
