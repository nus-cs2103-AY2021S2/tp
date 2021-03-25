package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class UndoTaskParserCommandTest {

    private UndoTaskCommandParser parser = new UndoTaskCommandParser();

    @Test
    public void parse_validArgs_returnsUndoTaskCommand() throws ParseException {
        assertParseSuccess(parser, "1", new UndoTaskCommand(ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UndoTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UndoTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1, 2 ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UndoTaskCommand.MESSAGE_USAGE));
    }
}
