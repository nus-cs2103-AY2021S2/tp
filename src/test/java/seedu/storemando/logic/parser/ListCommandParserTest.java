package seedu.storemando.logic.parser;

import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.ListCommand;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        ListCommand expectedListCommand = new ListCommand();
        assertParseSuccess(parser, "     ", expectedListCommand);
    }
}
