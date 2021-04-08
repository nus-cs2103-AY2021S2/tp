package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.weeblingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.weeblingo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.weeblingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.DeleteTagCommand;
import seedu.weeblingo.model.tag.Tag;

public class DeleteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE);

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        //no index specified
        assertParseFailure(parser, VALID_TAG_EASY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //String provided instead of number
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        //negative index
        assertParseFailure(parser, "-69" + VALID_TAG_EASY, MESSAGE_INVALID_FORMAT);

        //zero index
        assertParseFailure(parser, "0" + VALID_TAG_EASY, MESSAGE_INVALID_FORMAT);

        //invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        //invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        Set<Tag> emptyTag = Collections.EMPTY_SET;

        Set<Tag> oneTag = new HashSet<>();
        oneTag.add(new Tag(VALID_TAG_EASY));

        Set<Tag> twoTags = new HashSet<>();
        twoTags.add(new Tag(VALID_TAG_EASY));
        twoTags.add(new Tag(VALID_TAG_DIFFICULT));

        //only index provided
        assertParseSuccess(parser, "1", new DeleteTagCommand(INDEX_FIRST_FLASHCARD, emptyTag));

        //exactly one tag provided
        assertParseSuccess(parser, "1 t/easy", new DeleteTagCommand(INDEX_FIRST_FLASHCARD, oneTag));

        //more than one tag provided
        assertParseSuccess(parser, "1 t/easy t/difficult", new DeleteTagCommand(INDEX_FIRST_FLASHCARD, twoTags));
    }

}
