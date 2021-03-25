package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.NEGATIVE_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.OUT_OF_RANGE_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalIndexes.ZERO_INDEX_STRING;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectClearCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SelectIndexCommand;
import seedu.address.logic.commands.SelectShowCommand;

public class SelectCommandParserTest {

    private final SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_selectShown_success() {
        SelectCommand selectIndexCommand = new SelectIndexCommand();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SelectIndexCommandParser.SPECIAL_INDEX,
                selectIndexCommand);
    }

    @Test
    public void parse_selectIndexes_success() {
        SelectCommand selectIndexCommand = new SelectIndexCommand(VALID_INDEXES);
        String inputIndexes = VALID_INDEXES.stream()
                .map(Index::getOneBased).map(String::valueOf)
                .collect(Collectors.joining(" "));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + inputIndexes, selectIndexCommand);
    }

    @Test
    public void parse_selectShow_success() {
        SelectCommand selectShowCommand = new SelectShowCommand();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + SelectCommand.SHOW_SUB_COMMAND_WORD,
                selectShowCommand);
    }

    @Test
    public void parse_selectClear_success() {
        SelectCommand selectClearCommand = new SelectClearCommand();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + SelectCommand.CLEAR_SUB_COMMAND_WORD,
                selectClearCommand);
    }

    @Test
    public void parse_invalidSelect_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // show
        assertValidCommandToAliasSuccess(parser,
                PREAMBLE_WHITESPACE + SelectCommand.SHOW_SUB_COMMAND_WORD);

        // clear
        assertValidCommandToAliasSuccess(parser,
                PREAMBLE_WHITESPACE + SelectCommand.CLEAR_SUB_COMMAND_WORD);

        // shown
        assertValidCommandToAliasSuccess(parser,
                PREAMBLE_WHITESPACE + SelectIndexCommandParser.SPECIAL_INDEX);

        String inputIndexes = VALID_INDEXES.stream()
                .map(Index::getOneBased).map(String::valueOf)
                .collect(Collectors.joining(" "));

        // indexes
        assertValidCommandToAliasSuccess(parser,
                PREAMBLE_WHITESPACE + inputIndexes);
    }

    @Test
    public void parse_validAddCommandAlias_returnsFalse() {
        // invalid sub command
        assertValidCommandToAliasFailure(parser, INVALID_INDEX_STRING);

        // invalid index string
        assertValidCommandToAliasFailure(parser, NEGATIVE_INDEX_STRING);

        // out of range index
        assertValidCommandToAliasFailure(parser, OUT_OF_RANGE_INDEX_STRING);

        // zero index string
        assertValidCommandToAliasFailure(parser, ZERO_INDEX_STRING);
    }
}
