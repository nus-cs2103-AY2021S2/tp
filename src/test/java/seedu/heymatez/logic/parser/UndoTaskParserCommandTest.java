package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.UndoTaskCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;

/**
 * Contains unit tests for {@code UndoTaskCommandParser}.
 */
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
