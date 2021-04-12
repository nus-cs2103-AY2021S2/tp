package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteGroupmateCommand;

public class DeleteGroupmateCommandParserTest {
    private DeleteGroupmateCommandParser parser = new DeleteGroupmateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Index expectedGroupmateIndex = Index.fromOneBased(1);

        // all field appear once
        assertParseSuccess(parser, INDEX_FIRST.getOneBased() + " " + PREFIX_INDEX + " "
                        + INDEX_FIRST.getOneBased(),
                new DeleteGroupmateCommand(expectedProjectIndex, expectedGroupmateIndex)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupmateCommand.MESSAGE_USAGE);

        // missing project index
        assertParseFailure(parser, PREFIX_INDEX + " " + INDEX_FIRST.getOneBased(),
                expectedMessage);

        // missing remove prefix
        assertParseFailure(parser, "" + INDEX_FIRST.getOneBased(), expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project index
        assertParseFailure(parser, "0 " + PREFIX_INDEX + " "
                + INDEX_FIRST.getOneBased(), MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // invalid remove
        assertParseFailure(parser, INDEX_FIRST.getOneBased() + " " + PREFIX_INDEX + " 0",
                MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX);
    }
}
