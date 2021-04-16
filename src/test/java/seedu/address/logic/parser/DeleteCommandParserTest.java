package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.logic.parser.DeleteCommandParser.SELECTED;
import static seedu.address.logic.parser.DeleteCommandParser.SPECIAL_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.NEGATIVE_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.ZERO_INDEX_STRING;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgsSingleIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_INDEX_STRING,
                DeleteCommand.buildDeleteIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON)));
    }

    @Test
    public void parse_validArgsMultipleIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_INDEXES_STRING, DeleteCommand.buildDeleteIndexCommand(VALID_INDEXES));
    }

    @Test
    public void parse_shown_returnsDeleteCommand() {
        assertParseSuccess(parser, SPECIAL_INDEX, DeleteCommand.buildDeleteShownCommand());
    }

    @Test
    public void parse_selected_returnsDeleteCommand() {
        assertParseSuccess(parser, SELECTED, DeleteCommand.buildDeleteSelectedCommand());
    }

    @Test
    public void parse_validArgsShown_returnsDeleteCommand() {
        assertParseSuccess(parser, SPECIAL_INDEX, DeleteCommand.buildDeleteShownCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // valid index
        assertValidCommandToAliasSuccess(parser, VALID_INDEX_STRING);

        // shown
        assertValidCommandToAliasSuccess(parser, SPECIAL_INDEX);

        // selected
        assertValidCommandToAliasSuccess(parser, SELECTED);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        // negative index
        assertValidCommandToAliasFailure(parser, NEGATIVE_INDEX_STRING);

        // zero index
        assertValidCommandToAliasFailure(parser, ZERO_INDEX_STRING);

        // invalid arguments being parsed
        assertValidCommandToAliasFailure(parser, INVALID_INDEX_STRING);
    }
}
