package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateProjectCommand;
import seedu.address.model.project.ProjectName;

public class UpdateProjectCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateProjectCommand.MESSAGE_USAGE);

    private UpdateProjectCommandParser parser = new UpdateProjectCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no project index specified
        assertParseFailure(parser, VALID_PROJECT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateProjectCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateProjectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_PROJECT_NAME, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + VALID_PROJECT_NAME, MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + VALID_PROJECT_NAME,
                MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidProjectName_failure() {
        assertParseFailure(parser, VALID_INDEX_ONE + INVALID_PROJECT_NAME,
                ProjectName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldSpecifiedAndValid_success() {
        String userInput = VALID_INDEX_ONE + VALID_PROJECT_NAME;
        UpdateProjectCommand expectedCommand = new UpdateProjectCommand(INDEX_FIRST,
                new ProjectName("CS2103T team project"));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
