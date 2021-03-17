package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.INVALID_ALIAS_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAliasCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteAliasCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteAliasCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteAliasCommandParserTest {

    private DeleteAliasCommandParser parser = new DeleteAliasCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, ADD_ALIAS_STRING, new DeleteAliasCommand(ADD_ALIAS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_ALIAS_STRING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // valid alias
        assertValidCommandToAliasSuccess(parser, ADD_ALIAS_STRING);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        // invalid alias
        assertValidCommandToAliasFailure(parser, INVALID_ALIAS_STRING);
    }
}
