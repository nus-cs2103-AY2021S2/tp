package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearAssigneesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
}
