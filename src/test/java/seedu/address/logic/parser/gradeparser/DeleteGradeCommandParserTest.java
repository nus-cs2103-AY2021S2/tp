package seedu.address.logic.parser.gradeparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.gradecommands.DeleteGradeCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteGradeCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteGradeCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */

public class DeleteGradeCommandParserTest {

    private DeleteGradeCommandParser parser = new DeleteGradeCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteGradeCommand() {
        assertParseSuccess(parser, "1", new DeleteGradeCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroArg_throwsParseExceptinon() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteGradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeArg_throwsParseExceptinon() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteGradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
    }
}
