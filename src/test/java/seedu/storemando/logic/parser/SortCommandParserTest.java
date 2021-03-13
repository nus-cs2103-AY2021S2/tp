package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.storemando.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.storemando.logic.commands.SortCommand;
import seedu.storemando.logic.commands.SortExpiryDateCommand;
import seedu.storemando.logic.commands.SortQuantityCommand;

class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "     ", expectedMessage);
    }

    @Test
    public void parse_invalidArgument_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "location", expectedMessage);
        assertParseFailure(parser, "name", expectedMessage);
        assertParseFailure(parser, "item", expectedMessage);
        assertParseFailure(parser, "store", expectedMessage);
        assertParseFailure(parser, "expiry", expectedMessage);
        assertParseFailure(parser, "q", expectedMessage);
        assertParseFailure(parser, "quantities", expectedMessage);
        assertParseFailure(parser, "expirDate", expectedMessage);
        assertParseFailure(parser, "quant1ty", expectedMessage);
        assertParseFailure(parser, "room", expectedMessage);

    }

    @Test
    public void parse_validArgment_success() {
        SortExpiryDateCommand expectedExpiryDateCommand = new SortExpiryDateCommand();
        SortQuantityCommand expectedQuantityCommand = new SortQuantityCommand();

        //check for valid quantity user inputs
        assertParseSuccess(parser, "quantity", expectedQuantityCommand);
        assertParseSuccess(parser, "QUANTITY", expectedQuantityCommand);
        assertParseSuccess(parser, "QuanTITy", expectedQuantityCommand);
        assertParseSuccess(parser, "qUaNtItY", expectedQuantityCommand);

        //check for valid expiryDate user inputs
        assertParseSuccess(parser, "expiryDate", expectedExpiryDateCommand);
        assertParseSuccess(parser, "expirydate", expectedExpiryDateCommand);
        assertParseSuccess(parser, "EXPIRYDATE", expectedExpiryDateCommand);
        assertParseSuccess(parser, "EXPIRYdate", expectedExpiryDateCommand);
        assertParseSuccess(parser, "ExPiRyDaTe", expectedExpiryDateCommand);
    }
}
