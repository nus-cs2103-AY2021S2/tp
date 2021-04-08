package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static chim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static chim.testutil.TypicalIndexes.INDEX_LAST_ORDER;

import org.junit.jupiter.api.Test;

import chim.logic.commands.DoneCommand;

public class DoneCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE);

    private final DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_noArgs_throwsParseExeception() {
        // no index specific
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseExeception() {
        // no index , extra prefixes
        assertParseFailure(parser, "n/a", MESSAGE_INVALID_FORMAT);

        // negative index
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);

        // valid index but extra prefixes
        assertParseFailure(parser, "1 n/a", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validIndex_returnsDoneCommand() {
        //First index
        assertParseSuccess(parser, "1", new DoneCommand(INDEX_FIRST_ORDER));

        //Last Index
        assertParseSuccess(parser, INDEX_LAST_ORDER.getOneBased() + "", new DoneCommand(INDEX_LAST_ORDER));
    }
}
