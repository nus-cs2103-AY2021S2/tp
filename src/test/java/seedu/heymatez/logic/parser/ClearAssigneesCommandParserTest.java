package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.ClearAssigneesCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;

/**
 * Contains unit tests for {@code ClearAssigneeCommandParser}.
 */
public class ClearAssigneesCommandParserTest {
    private ClearAssigneesCommandParser parser = new ClearAssigneesCommandParser();

    @Test
    public void parse_validArgs_returnsClearedTaskCommand() throws ParseException {
        assertParseSuccess(parser, "1", new ClearAssigneesCommand(ParserUtil.parseIndex("1")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearAssigneesCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearAssigneesCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1, 2 ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearAssigneesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInteger_throwsParseException() {

        assertParseFailure(parser, "0", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertParseFailure(parser, "-1", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
