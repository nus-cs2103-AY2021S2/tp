package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.TypicalTags.getTypicalTags;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.testutil.IndexesUtil;
import seedu.address.testutil.TagsUtil;
import seedu.address.testutil.TypicalIndexes;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTagCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTagCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTagCommandParserTest {

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validDeleteFromTargetIndexesArgs_returnsDeleteTagCommand() {
        String commandString = IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES) + " "
                + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseSuccess(parser, commandString,
                DeleteTagCommand.createWithTargetIndexes(TypicalIndexes.VALID_INDEXES, getTypicalTags()));
    }

    @Test
    public void parse_validDeleteFromShownIndexArgs_returnsDeleteTagCommand() {
        String commandString = DeleteTagCommandParser.SHOWN_INDEX + " " + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseSuccess(parser, commandString, DeleteTagCommand.createWithShownIndex(getTypicalTags()));
    }

    @Test
    public void parse_validDeleteFromSelectedIndexArgs_returnsDeleteTagCommand() {
        String commandString = DeleteTagCommandParser.SELECTED_INDEX + " " + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseSuccess(parser, commandString, DeleteTagCommand.createWithSelectedIndex(getTypicalTags()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing indexes
        String commandString = " " + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));

        // missing tags with typical indexes
        commandString = IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES) + " ";
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));

        // missing tags with shown index
        commandString = DeleteTagCommandParser.SHOWN_INDEX + " ";
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));

        // missing tags with select index
        commandString = DeleteTagCommandParser.SELECTED_INDEX + " ";
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDeleteTagCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // valid indexes
        assertValidCommandToAliasSuccess(parser, TypicalIndexes.VALID_INDEX_STRING);
        assertValidCommandToAliasSuccess(parser, IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES));
        assertValidCommandToAliasSuccess(parser, DeleteTagCommandParser.SHOWN_INDEX);
        assertValidCommandToAliasSuccess(parser, DeleteTagCommandParser.SELECTED_INDEX);

        // valid indexes and tags
        assertValidCommandToAliasSuccess(parser, IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES)
                + TAG_DESC_HUSBAND);
        assertValidCommandToAliasSuccess(parser, DeleteTagCommandParser.SHOWN_INDEX
                + TAG_DESC_HUSBAND);
        assertValidCommandToAliasSuccess(parser, DeleteTagCommandParser.SELECTED_INDEX
                + TAG_DESC_HUSBAND);

        // valid indexes and empty last tag
        assertValidCommandToAliasSuccess(parser, IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES)
                + TAG_DESC_FRIEND + EMPTY_TAG_DESC);
        assertValidCommandToAliasSuccess(parser, DeleteTagCommandParser.SHOWN_INDEX
                + TAG_DESC_FRIEND + EMPTY_TAG_DESC);
        assertValidCommandToAliasSuccess(parser, DeleteTagCommandParser.SELECTED_INDEX
                + TAG_DESC_FRIEND + EMPTY_TAG_DESC);
    }

    @Test
    public void parse_invalidDeleteTagCommandAlias_returnsFalse() {
        // invalid indexes
        assertValidCommandToAliasFailure(parser, TypicalIndexes.INVALID_INDEX_STRING);

        // invalid tags
        assertValidCommandToAliasFailure(parser, IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES)
                + INVALID_TAG_DESC);

        // empty tag in the middle
        assertValidCommandToAliasFailure(parser, IndexesUtil.getIndexesDetails(TypicalIndexes.VALID_INDEXES)
                + TAG_DESC_HUSBAND + EMPTY_TAG_DESC + TAG_DESC_FRIEND);
    }
}
