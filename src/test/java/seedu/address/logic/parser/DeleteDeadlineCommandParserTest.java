package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_TASK_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDeadlineCommand;

public class DeleteDeadlineCommandParserTest {

    private DeleteDeadlineCommandParser parser = new DeleteDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Index expectedDeadlineIndex = Index.fromOneBased(1);

        String userInput = INDEX_FIRST.getOneBased() + " " + PREFIX_REMOVE_TASK_INDEX + " " + INDEX_FIRST.getOneBased();

        DeleteDeadlineCommand command = new DeleteDeadlineCommand(expectedProjectIndex, expectedDeadlineIndex);

        // all field appear once
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeadlineCommand.MESSAGE_USAGE);

        String userInputMissingProject = PREFIX_REMOVE_TASK_INDEX + " " + INDEX_FIRST.getOneBased();

        // missing project index
        assertParseFailure(parser, userInputMissingProject, expectedMessage);

        String userInputMissingPrefix = INDEX_FIRST.getOneBased() + " " + INDEX_FIRST.getOneBased();

        // missing remove prefix
        assertParseFailure(parser, userInputMissingPrefix, expectedMessage);

        String userInputMissingDeadline = INDEX_FIRST.getOneBased() + " " + PREFIX_REMOVE_TASK_INDEX;
        String expectedMessageMissingDeadline = "Index is not a non-zero unsigned integer.";

        // missing remove prefix
        assertParseFailure(parser, userInputMissingDeadline, expectedMessageMissingDeadline);
    }

    @Test
    public void parse_invalidValue_failure() {
        Index validProjectIndex = Index.fromOneBased(1);
        Index validDeadlineIndex = Index.fromOneBased(1);

        String userInputInvalidProjectIndex = "0 " + PREFIX_REMOVE_TASK_INDEX + " "
                + validDeadlineIndex;

        // invalid project index
        assertParseFailure(parser, userInputInvalidProjectIndex, MESSAGE_INVALID_INDEX);

        String userInputInvalidDeadlineIndex = validProjectIndex + " " + PREFIX_REMOVE_TASK_INDEX + " 0";

        // invalid remove deadline index
        assertParseFailure(parser, userInputInvalidDeadlineIndex,
                MESSAGE_INVALID_INDEX);

        String userInputInvalidProjectIndexExceeded = "10 " + PREFIX_REMOVE_TASK_INDEX + " "
                + validDeadlineIndex;

        // invalid project index
        assertParseFailure(parser, userInputInvalidProjectIndexExceeded, MESSAGE_INVALID_INDEX);

        String userInputInvalidDeadlineIndexExceeded = validProjectIndex + " " + PREFIX_REMOVE_TASK_INDEX + " 10";

        // invalid remove deadline index
        assertParseFailure(parser, userInputInvalidDeadlineIndexExceeded,
                MESSAGE_INVALID_INDEX);
    }

}
