package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.heymatez.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.heymatez.logic.commands.DeleteMemberCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;

/**
 * Contains unit tests for {@code DeleteMemberCommandParser}.
 */
public class DeleteMemberCommandParserTest {

    private DeleteMemberCommandParser parser = new DeleteMemberCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() throws ParseException {
        assertParseSuccess(parser, "Amy Bee",
                new DeleteMemberCommand(ParserUtil.parseName(VALID_NAME_AMY)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteMemberCommand.MESSAGE_USAGE));
    }
}
