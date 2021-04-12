package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_SECOND_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.ShowCommand;

public class ShowCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT_NAN = String.format(MESSAGE_INVALID_COMMAND_ERROR,
            MESSAGE_INVALID_INDEX, ShowCommand.MESSAGE_USAGE);

    private ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowCommand.MESSAGE_USAGE + "\n"));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1000000000000000000", MESSAGE_INVALID_FORMAT_NAN); // overflow
        assertParseFailure(parser, "-99999999999999999", MESSAGE_INVALID_FORMAT_NAN); // underflow
        assertParseFailure(parser, "1c", MESSAGE_INVALID_FORMAT_NAN); // invalid number
        assertParseFailure(parser, "one", MESSAGE_INVALID_FORMAT_NAN); // invalid number (should be numeric)
    }

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENDPOINT;
        String userInput = targetIndex.getOneBased() + "";
        ShowCommand expectedCommand = new ShowCommand(targetIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
