package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_DOG;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.DeleteDogCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteDogCommandParserTest {

    private DeleteDogCommandParser parser = new DeleteDogCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteDogCommand(ID_FIRST_DOG));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDogCommand.MESSAGE_USAGE));
    }
}
