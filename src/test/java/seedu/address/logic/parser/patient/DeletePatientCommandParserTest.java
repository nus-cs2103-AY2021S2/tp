package seedu.address.logic.parser.patient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.patient.DeletePatientCommand.FORCE_DELETE_MESSAGE_USAGE;
import static seedu.address.logic.commands.patient.DeletePatientCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.patient.DeletePatientCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeletePatientCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeletePatientCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePatientCommandParserTest {

    private DeletePatientCommandParser parser = new DeletePatientCommandParser();

    @Test
    public void parse_nonForceValidArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePatientCommand(INDEX_FIRST_IN_LIST, false));
    }

    @Test
    public void parse_forceValidArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "--force 1",
                new DeletePatientCommand(INDEX_FIRST_IN_LIST, true));
    }

    @Test
    public void parse_nonForceInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_forceInvalidArgs_throwsParseException() {
        assertParseFailure(parser, "--force a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FORCE_DELETE_MESSAGE_USAGE));
    }

    @Test
    public void parse_nonForceNoArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_forceNoArgs_throwsParseException() {
        assertParseFailure(parser, "--force",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FORCE_DELETE_MESSAGE_USAGE));
    }
}
