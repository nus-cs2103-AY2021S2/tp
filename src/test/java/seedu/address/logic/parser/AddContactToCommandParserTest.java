package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_STANDALONE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INDEX_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactToCommand;

public class AddContactToCommandParserTest {
    private AddContactToCommandParser parser = new AddContactToCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Index expectedContactIndex = Index.fromOneBased(2);

        // all field appear once
        assertParseSuccess(
                parser,
                INDEX_STANDALONE_ONE + INDEX_DESC_TWO,
                new AddContactToCommand(expectedProjectIndex, expectedContactIndex)
        );

        // multiple indices - last index accepted
        assertParseSuccess(parser,
                INDEX_STANDALONE_ONE + INDEX_DESC_TWO + INDEX_DESC_TWO,
                new AddContactToCommand(expectedProjectIndex, expectedContactIndex)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactToCommand.MESSAGE_USAGE);

        // missing index prefix
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INDEX_STANDALONE_TWO,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, INDEX_STANDALONE_ONE + INVALID_INDEX_DESC, MESSAGE_INVALID_INDEX);
    }
}
