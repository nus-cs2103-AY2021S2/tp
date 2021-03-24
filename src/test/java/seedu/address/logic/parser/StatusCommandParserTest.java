package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLEAN_TAG_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class StatusCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE);

    private StatusCommandParser parser = new StatusCommandParser();

    @Test
    public void parse_status_success() throws ParseException {
        String statusClean = "clean";
        String statusUnclean = "unclean";
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(ParserUtil.parseIndex("1"));
        indexArray.add(ParserUtil.parseIndex("2"));
        StatusCommand expectedCommand = new StatusCommand(indexArray , statusClean);
        assertParseSuccess(parser, statusClean + " 1 2", expectedCommand);
        expectedCommand = new StatusCommand(indexArray , statusUnclean);
        assertParseSuccess(parser, statusUnclean + " 1 2", expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no status and index array
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index array
        assertParseFailure(parser, "clean ", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "unclean ", MESSAGE_INVALID_FORMAT);

        // no status while only input one index
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidCleanStatus_failure() {
        // input invalid clean status and index array
        assertParseFailure(parser, INVALID_CLEAN_TAG_DESC + " 1 2 ", StatusCommand.MESSAGE_ERROR_STATUS);

        //input two or more index
        assertParseFailure(parser, "1 2", StatusCommand.MESSAGE_ERROR_STATUS);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "clean" + " w 2", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "clean" + " 1 2 SS", MESSAGE_INVALID_INDEX);
    }

}
