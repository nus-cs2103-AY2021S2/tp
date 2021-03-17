package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
//@@author mesyeux
public class DeleteTaskCommandParserTest {
    //@@author

    //@@author mesyeux
    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();
    //@@author

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        //@@author mesyeux
        assertParseSuccess(parser, "1", new DeleteTaskCommand(INDEX_FIRST_TASK));
        //@@author
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //@@author mesyeux
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE));
        //@@author
    }
}
