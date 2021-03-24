package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INVALID_INDEX_STRING;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SelectIndexCommand;

public class SelectIndexCommandParserTest {

    private final SelectIndexCommandParser parser = new SelectIndexCommandParser();

    @Test
    public void parse_selectShown_success() {
        SelectCommand selectIndexCommand = new SelectIndexCommand();
        assertParseSuccess(parser, SelectIndexCommandParser.SPECIAL_INDEX,
                selectIndexCommand);
    }

    @Test
    public void parse_validIndexes_success() {
        SelectCommand selectIndexCommand = new SelectIndexCommand(VALID_INDEXES);
        String inputIndexes = VALID_INDEXES.stream()
                .map(Index::getOneBased).map(String::valueOf)
                .collect(Collectors.joining(" "));
        assertParseSuccess(parser, inputIndexes, selectIndexCommand);
    }

    @Test
    public void parse_invalidIndexes_failure() {
        assertParseFailure(parser, INVALID_INDEX_STRING, SelectCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", SelectCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidSubCommand_failure() {
        assertParseFailure(parser, "invalid subcommand", SelectCommand.MESSAGE_USAGE);
    }
}
