package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDateCommand;

public class DeleteDateCommandParserTest {

    private final DeleteDateCommandParser parser = new DeleteDateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 i/1", new DeleteDateCommand(Index.fromOneBased(1), Index.fromOneBased(1)));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDateCommand.MESSAGE_USAGE);

        // missing person index
        assertParseFailure(parser, "i/1", expectedMessage);

        // missing date index
        assertParseFailure(parser, "1", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDateCommand.MESSAGE_USAGE);

        // invalid person index format
        assertParseFailure(parser, "a i/1", expectedMessage);
    }
}
