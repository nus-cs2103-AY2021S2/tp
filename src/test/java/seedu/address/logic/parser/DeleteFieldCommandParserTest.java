//@@author mesyeux
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteFieldCommand;

public class DeleteFieldCommandParserTest {
    private DeleteFieldCommandParser parser = new DeleteFieldCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " 1 d/", new DeleteFieldCommand(INDEX_FIRST_TASK, "d/"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFieldCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFieldCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 d/ d/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFieldCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 pp/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFieldCommand.MESSAGE_USAGE));
    }
}
