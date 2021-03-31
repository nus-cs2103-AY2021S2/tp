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

import seedu.address.logic.commands.AddTagCommand;
import seedu.address.testutil.TagsUtil;
import seedu.address.testutil.TypicalIndexes;

public class AddTagCommandParserTest {

    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_validAddToTargetIndexesArgs_returnsAddTagCommand() {
        String commandString = TypicalIndexes.VALID_INDEXES_STRING + " "
                + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseSuccess(parser, commandString,
                AddTagCommand.createWithTargetIndexes(TypicalIndexes.VALID_INDEXES, getTypicalTags()));
    }

    @Test
    public void parse_validAddToShownIndexArgs_returnsAddTagCommand() {
        String commandString = AddTagCommandParser.SHOWN_INDEX + " " + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseSuccess(parser, commandString, AddTagCommand.createWithShownIndex(getTypicalTags()));
    }

    @Test
    public void parse_validAddToSelectedIndexArgs_returnsAddTagCommand() {
        String commandString = AddTagCommandParser.SELECTED_INDEX + " " + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseSuccess(parser, commandString, AddTagCommand.createWithSelectedIndex(getTypicalTags()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing indexes
        String commandString = " " + TagsUtil.getTagsDetails(getTypicalTags());
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));

        // missing tags with typical indexes
        commandString = TypicalIndexes.VALID_INDEXES_STRING + " ";
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));

        // missing tags with shown index
        commandString = AddTagCommandParser.SHOWN_INDEX + " ";
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));

        // missing tags with select index
        commandString = AddTagCommandParser.SELECTED_INDEX + " ";
        assertParseFailure(parser, commandString,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAddTagCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // valid indexes
        assertValidCommandToAliasSuccess(parser, TypicalIndexes.VALID_INDEX_STRING);
        assertValidCommandToAliasSuccess(parser, TypicalIndexes.VALID_INDEXES_STRING);
        assertValidCommandToAliasSuccess(parser, AddTagCommandParser.SHOWN_INDEX);
        assertValidCommandToAliasSuccess(parser, AddTagCommandParser.SELECTED_INDEX);

        // valid indexes and tags
        assertValidCommandToAliasSuccess(parser, TypicalIndexes.VALID_INDEXES_STRING + TAG_DESC_HUSBAND);
        assertValidCommandToAliasSuccess(parser, AddTagCommandParser.SHOWN_INDEX
                + TAG_DESC_HUSBAND);
        assertValidCommandToAliasSuccess(parser, AddTagCommandParser.SELECTED_INDEX
                + TAG_DESC_HUSBAND);

        // valid indexes and empty last tag
        assertValidCommandToAliasSuccess(parser, TypicalIndexes.VALID_INDEXES_STRING
                + TAG_DESC_FRIEND + EMPTY_TAG_DESC);
        assertValidCommandToAliasSuccess(parser, AddTagCommandParser.SHOWN_INDEX
                + TAG_DESC_FRIEND + EMPTY_TAG_DESC);
        assertValidCommandToAliasSuccess(parser, AddTagCommandParser.SELECTED_INDEX
                + TAG_DESC_FRIEND + EMPTY_TAG_DESC);
    }

    @Test
    public void parse_invalidAddTagCommandAlias_returnsFalse() {
        // invalid indexes
        assertValidCommandToAliasFailure(parser, TypicalIndexes.INVALID_INDEX_STRING);

        // invalid tags
        assertValidCommandToAliasFailure(parser, TypicalIndexes.VALID_INDEXES_STRING
                + INVALID_TAG_DESC);

        // empty tag in the middle
        assertValidCommandToAliasFailure(parser, TypicalIndexes.VALID_INDEXES_STRING
                + TAG_DESC_HUSBAND + EMPTY_TAG_DESC + TAG_DESC_FRIEND);
    }
}
