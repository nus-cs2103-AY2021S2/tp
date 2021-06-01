package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteEventCommand;

public class DeleteEventCommandParserTest {

    private DeleteEventCommandParser parser = new DeleteEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedProjectIndex = Index.fromOneBased(1);
        Index expectedEventIndex = Index.fromOneBased(1);

        String userInput = INDEX_FIRST.getOneBased() + " " + PREFIX_INDEX + " " + INDEX_FIRST.getOneBased();

        DeleteEventCommand command = new DeleteEventCommand(expectedProjectIndex, expectedEventIndex);

        // all field appear once
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE);

        String userInputMissingProject = PREFIX_INDEX + " " + INDEX_FIRST.getOneBased();

        // missing project index
        assertParseFailure(parser, userInputMissingProject, expectedMessage);

        String userInputMissingPrefix = INDEX_FIRST.getOneBased() + " " + INDEX_FIRST.getOneBased();

        // missing remove prefix
        assertParseFailure(parser, userInputMissingPrefix, expectedMessage);

        String userInputMissingEvent = INDEX_FIRST.getOneBased() + " " + PREFIX_INDEX;

        // missing remove prefix
        assertParseFailure(parser, userInputMissingEvent, MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        String validProjectIndex = "1";
        String validEventIndex = "1";

        String userInputInvalidProjectIndex = "0 " + PREFIX_INDEX + " "
                + validEventIndex;

        // invalid project index
        assertParseFailure(parser, userInputInvalidProjectIndex, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        String userInputInvalidEventIndex = validProjectIndex + " " + PREFIX_INDEX + " 0";

        // invalid remove event index
        assertParseFailure(parser, userInputInvalidEventIndex,
                MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

}
