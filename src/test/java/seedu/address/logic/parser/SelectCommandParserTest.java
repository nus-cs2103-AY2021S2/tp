package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;

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
}
