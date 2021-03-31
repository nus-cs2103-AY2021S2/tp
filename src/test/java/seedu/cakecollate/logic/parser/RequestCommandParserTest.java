package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cakecollate.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.logic.commands.RequestCommand;
import seedu.cakecollate.model.order.Request;

public class RequestCommandParserTest {
    private RequestCommandParser parser = new RequestCommandParser();
    private final String nonEmptyRequest = "Some request.";

    @Test
    public void parse_indexSpecified_success() {
        // have Request
        Index targetIndex = INDEX_FIRST_ORDER;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REQUEST + nonEmptyRequest;
        RequestCommand expectedCommand = new RequestCommand(INDEX_FIRST_ORDER, new Request(nonEmptyRequest));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no Request
        userInput = targetIndex.getOneBased() + " " + PREFIX_REQUEST;
        expectedCommand = new RequestCommand(INDEX_FIRST_ORDER, new Request(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RequestCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RequestCommand.COMMAND_WORD + " " + INDEX_FIRST_ORDER
                + " " + PREFIX_REQUEST , expectedMessage);

        // no index
        assertParseFailure(parser, RequestCommand.COMMAND_WORD + " " + PREFIX_REQUEST
                + nonEmptyRequest, expectedMessage);
    }
}
