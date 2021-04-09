package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteSessionCommand;

public class DeleteSessionCommandParserTest {
    private static DeleteSessionCommandParser parser = new DeleteSessionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteSessionCommand expectedCommand = new DeleteSessionCommand(ALICE.getName(), INDEX_FIRST_SESSION);

        assertParseSuccess(parser, String.format(" n/%s i/1", ALICE.getName().toString()),
                expectedCommand);

        assertParseSuccess(parser, String.format("    n/%s    i/1", ALICE.getName().toString()),
                expectedCommand);
    }

    @Test
    public void parse_invalidField_throwParseException() {
        //missing name
        assertParseFailure(parser, " i/1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));

        //missing index
        assertParseFailure(parser, String.format(" n/%2s", ALICE.getName().toString()),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));
    }
}
